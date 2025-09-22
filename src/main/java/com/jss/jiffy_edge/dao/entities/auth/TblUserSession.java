package com.jss.jiffy_edge.dao.entities.auth;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_user_session")
public class TblUserSession {

	@Id
    @Column(name = "session_id", length = 100, nullable = false, unique = true)
    private String sessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private TblUsers user;
    
    @Column(name = "role_id")
	private Integer roleId;

    @Column(name = "jwt_token", columnDefinition = "TEXT")
    private String jwtToken;

    @Column(name = "device_details", length = 255)
    private String deviceDetails;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "geo_city", length = 100)
    private String geoCity;

    @Column(name = "geo_country", length = 100)
    private String geoCountry;
    
    @Column(name = "geo_state", length = 100)
    private String geoState;
    
    @Column(name = "session_duration_minutes")
    private Long sessionDurationMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SessionStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "last_accessed_at")
    private LocalDateTime lastAccessedAt;

    public enum SessionStatus {
        ACTIVE, EXPIRED, LOGGED_OUT
    }

    // Getters and Setters
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public TblUsers getUser() {
		return user;
	}

	public void setUser(TblUsers user) {
		this.user = user;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getDeviceDetails() {
		return deviceDetails;
	}

	public void setDeviceDetails(String deviceDetails) {
		this.deviceDetails = deviceDetails;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getGeoCity() {
		return geoCity;
	}

	public void setGeoCity(String geoCity) {
		this.geoCity = geoCity;
	}

	public String getGeoCountry() {
		return geoCountry;
	}

	public void setGeoCountry(String geoCountry) {
		this.geoCountry = geoCountry;
	}

	public String getGeoState() {
		return geoState;
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState;
	}

	public Long getSessionDurationMinutes() {
		return sessionDurationMinutes;
	}

	public void setSessionDurationMinutes(Long sessionDurationMinutes) {
		this.sessionDurationMinutes = sessionDurationMinutes;
	}

	public SessionStatus getStatus() {
		return status;
	}

	public void setStatus(SessionStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public LocalDateTime getLastAccessedAt() {
		return lastAccessedAt;
	}

	public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
		this.lastAccessedAt = lastAccessedAt;
	}
}