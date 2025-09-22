package com.jss.jiffy_edge.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
	private static final String SECRET_KEY_BASE64 = "pW3V2Dn4bGFyZyNtVGFkK21GczVvVWZrNlBQa2R5TU0";

	private SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY_BASE64);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(Integer userId, String email, String role, Integer roleId, String sessionId) {
		return Jwts.builder().subject(email).claim("role", role).claim("roleId", roleId).claim("userId", userId)
				.claim("sessionId", sessionId).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) 
				.signWith(getSigningKey()).compact();
	}

	public String extractUserRole(String token) {
		return extractClaim(token, claims -> claims.get("role", String.class));
	}

	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public Integer getUserIdFromToken(String token) {
		return extractClaim(token, claims -> claims.get("userId", Integer.class));
	}

	public Integer getRoleIdFromToken(String token) {
		return extractClaim(token, claims -> claims.get("roleId", Integer.class));
	}

	public String getSessionIdFromToken(String token) {
		return extractClaim(token, claims -> claims.get("sessionId", String.class));
	}

	public boolean isValidToken(String token) {
		try {
			extractAllClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean validateToken(String token, String userEmail) {
		final String email = extractEmail(token);
		return (email.equals(userEmail) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
}