package com.jss.jiffy_edge.services.auth;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.json.gson.GsonFactory;

import com.jss.jiffy_edge.dao.entities.auth.TblUsers;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.dao.repo.auth.TblUserRolesRepository;
import com.jss.jiffy_edge.dao.repo.auth.TblUsersRepository;

@Service
public class GoogleAuthServiceImpl {

	private static final String GOOGLE_CLIENT_ID = "718629409608-esd92b3isih1jmvet95pmlfjojqnv9qh.apps.googleusercontent.com";

	@Autowired
	private TblUsersRepository userRepository;

	@Autowired
	private TblUserRolesRepository roleRepository;

	public TblUsers verifyAndLoginUser(String token) throws GeneralSecurityException, IOException {
		try {

			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
					GsonFactory.getDefaultInstance()).setAudience(Collections.singletonList(GOOGLE_CLIENT_ID)).build();

			GoogleIdToken idToken = verifier.verify(token);

			if (idToken == null) {
				throw new RuntimeException("Invalid Google token!");
			}

			GoogleIdToken.Payload payload = idToken.getPayload();
			String email = payload.getEmail();
			String name = (String) payload.get("name");
			String picture = (String) payload.get("picture");

			Optional<TblUsers> existingUser = userRepository.findByEmail(email);
			TblUsers user;

			if (existingUser.isPresent()) {
				user = existingUser.get();


				if (user.getStatus() != TblUsers.UserStatus.ACTIVE) {
					throw new RuntimeException("User account is not active.");
				}


				if (user.getRole().getStatus() != TblUserRoles.UserStatus.ACTIVE) {
					throw new RuntimeException("User role is not active.");
				}

			} else {
				user = new TblUsers();
				user.setEmail(email);
				user.setLoginName(name);
				user.setName(name);
				user.setPassword("SOCIAL_LOGIN");
				user.setStatus(TblUsers.UserStatus.ACTIVE);
				user.setSocialMediaProfile(picture);
				user.setSocialMediaAccessToken(token);
				user.setIsSocialMediaLogin(true);
				user.setMobile(null);
				user.setPassword("NA");
				user.setCompany("self-employed");
				user.setUserCatg(TblUsers.UserCategory.EXTERNAL_USER);
				user.setCreationDate(new Date());
				user.setLastUpdate(new Date());

				Optional<TblUserRoles> defaultRoleOpt = roleRepository.findByRoleName("ROOT USER");

				if (defaultRoleOpt.isPresent()) {
					user.setRole(defaultRoleOpt.get());
				} else {
					throw new RuntimeException("Default role not found.");
				}

				userRepository.save(user);
			}

			return user;

		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw e;
			}
			throw new RuntimeException("Google Token verification failed! " + e.getMessage());
		}
	}

}