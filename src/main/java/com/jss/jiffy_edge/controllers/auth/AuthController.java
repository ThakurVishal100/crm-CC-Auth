package com.jss.jiffy_edge.controllers.auth;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jss.jiffy_edge.config.JwtUtil;
import com.jss.jiffy_edge.dao.entities.auth.TblUsers;
import com.jss.jiffy_edge.dao.repo.auth.TblUsersRepository;
import com.jss.jiffy_edge.models.auth.LoginResponse;
import com.jss.jiffy_edge.models.auth.PolicyResponse;
import com.jss.jiffy_edge.models.auth.UserLoginRequest;
import com.jss.jiffy_edge.models.auth.UserResponse;
import com.jss.jiffy_edge.models.auth.UserSignupRequest;
import com.jss.jiffy_edge.models.auth.VerifyOtpRequestDTO;
import com.jss.jiffy_edge.services.auth.AuthService;
import com.jss.jiffy_edge.services.auth.GoogleAuthServiceImpl;
import com.jss.jiffy_edge.services.auth.OtpService;
import com.jss.jiffy_edge.services.auth.PolicyService;
import com.jss.jiffy_edge.services.auth.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "*", maxAge = 3600L)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private GoogleAuthServiceImpl googleAuthService;

	@Autowired
	private OtpService otpService;

	@Autowired
	private TblUsersRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PolicyService policyService;

	@PostMapping("/signup")
	public UserResponse signup(@RequestBody UserSignupRequest request) {
		return authService.signup(request);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest loginRequest, HttpSession session,
											   HttpServletRequest httpRequest) {
		UserResponse userResponse = authService.login(loginRequest);
		String jwtToken = jwtUtil.generateToken(userResponse.getUserId(), userResponse.getEmail(),
				userResponse.getRoleName(), userResponse.getRoleId(), session.getId());

		sessionService.createNewSession(session.getId(), userResponse.getUserId(), userResponse.getRoleId(), jwtToken,
				httpRequest, loginRequest);

		List<PolicyResponse> menus = policyService.getAppliedPoliciesByRoleId(userResponse.getRoleId());
		session.setAttribute("menus", menus);

		return ResponseEntity.ok(new LoginResponse(jwtToken, menus));
	}

	@PostMapping("/google-signin")
	public ResponseEntity<?> googleSignIn(@RequestBody Map<String, String> requestBody, HttpSession session,
										  HttpServletRequest httpRequest) {
		Map<String, Object> response = new HashMap<>();
		try {

			String googleToken = requestBody.get("credential");
			if (googleToken == null || googleToken.isBlank()) {
				return ResponseEntity.badRequest().body(Map.of("message", "Google token is required."));
			}

			TblUsers user = googleAuthService.verifyAndLoginUser(googleToken);

			String jwtToken = jwtUtil.generateToken(user.getUserId(), user.getEmail(), user.getRole().getRoleName(),
					user.getRole().getRoleId(), session.getId());

			sessionService.createNewSession(session.getId(), user.getUserId(), user.getRole().getRoleId(), jwtToken,
					httpRequest, null);

			List<PolicyResponse> menus = policyService.getAppliedPoliciesByRoleId(user.getRole().getRoleId());
			session.setAttribute("menus", menus);

			response.put("token", jwtToken);
			response.put("userId", user.getUserId());
			response.put("name", user.getName());
			response.put("email", user.getEmail());
			response.put("role", user.getRole().getRoleName());
			response.put("roleId", user.getRole().getRoleId());
			response.put("menus", menus);
			response.put("message", "Sign-in with Google successful!");

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.put("message", "Google sign-in failed: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

	@PostMapping("/send-otp")
	public ResponseEntity<String> sendOtp(@RequestParam String email) {
		String otp = String.valueOf(new Random().nextInt(899999) + 100000);

		TblUsers user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		user.setOtp(otp);
		userRepository.save(user);

		otpService.sendOtpEmail(email, otp);

		return ResponseEntity.ok("OTP sent to " + email + ". Your OTP is: " + otp);
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequestDTO request) {
		Map<String, Object> response = new HashMap<>();
		try {
			TblUsers user = userRepository.findByEmail(request.getEmail())
					.orElseThrow(() -> new RuntimeException("User not found"));

			if (user.getOtp() == null || !user.getOtp().equals(request.getOtp())) {
				response.put("message", "Invalid OTP");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}

			user.setOtp(null);
			user.setLastOtpVerifiedAt(LocalDateTime.now());
			userRepository.save(user);

			response.put("message", "OTP verified successfully");
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.put("message", "OTP verification failed: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}