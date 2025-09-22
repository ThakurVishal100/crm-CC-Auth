package com.jss.jiffy_edge.services.auth;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendOtpEmail(String toEmail, String otpCode) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("Your OTP Code");
		message.setText("Your OTP code is: " + otpCode + "\nIt is valid for 5 minutes.");

		mailSender.send(message);
	}
	
	 private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
	    private final Map<String, Long> otpTimestamps = new ConcurrentHashMap<>();

	    private static final long OTP_VALID_DURATION = 5 * 60 * 1000; 

	    public String generateOtp(String email) {
	        String otp = String.format("%06d", new Random().nextInt(999999));
	        otpStorage.put(email, otp);
	        otpTimestamps.put(email, System.currentTimeMillis());
	        return otp;
	    }

	    public boolean verifyOtp(String email, String otp) {
	        if (!otpStorage.containsKey(email)) {
	            return false; 
	        }

	        long currentTime = System.currentTimeMillis();
	        long otpTime = otpTimestamps.get(email);

	        if (currentTime - otpTime > OTP_VALID_DURATION) {
	            otpStorage.remove(email);
	            otpTimestamps.remove(email);
	            return false; 
	        }

	        if (otp.equals(otpStorage.get(email))) {
	            otpStorage.remove(email); 
	            otpTimestamps.remove(email);
	            return true;
	        }
	        return false;
	    }
}
