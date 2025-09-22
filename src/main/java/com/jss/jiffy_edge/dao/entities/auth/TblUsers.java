package com.jss.jiffy_edge.dao.entities.auth;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_users")

public class TblUsers {

	public enum UserCategory {
		EXTERNAL_USER, SYSTEM_USER
	}

	public enum UserStatus {
		ACTIVE, INACTIVE, DELETED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_catg", nullable = false)
	private UserCategory userCatg;

//	@Column(name = "role_id")
//	private Integer roleId;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
	@JsonBackReference
	private TblUserRoles role;

	@Column(name = "last_otp_verified_at")
	private LocalDateTime lastOtpVerifiedAt;

	@Column(name = "email", unique = true, nullable = false, length = 191)
	private String email;

	@Column(name = "mobile", unique = false, nullable = true, length = 20)
	private String mobile;

//	@Column(name = "token")
//	private String token;

	@Column(name = "address", columnDefinition = "TEXT")
	private String address;

	@Column(name = "company", length = 255)
	private String company;

	@Column(name = "password", nullable = false, length = 191)
	private String password;

	@Column(name = "is_social_media_login", columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean isSocialMediaLogin;

	@Column(name = "login_name", unique = true, nullable = true, length = 191)
	private String loginName;

	@Column(name = "social_media_profile", length = 255)
	private String socialMediaProfile;

	@Column(name = "social_media_access_token", length = 2048)
	private String socialMediaAccessToken;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "social_media_access_expiry")
	private Date socialMediaAccessExpiry;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "profile_image", length = 255)
	private String profileImage;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update")
	private Date lastUpdate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private UserStatus userStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private Date creationDate;

	@Column(name = "otp")
	private String otp;

//	@Column(name = "active_whatsapp_chats", nullable = false, columnDefinition = "int default 0")
//    private int activeWhatsappChats = 0;

	// Getter and Setter for OTP
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	// Getters and Setters

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserCategory getUserCatg() {
		return userCatg;
	}

	public void setUserCatg(UserCategory userCatg) {
		this.userCatg = userCatg;
	}

//	public Integer getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(Integer roleId) {
//		this.roleId = roleId;
//	}

	public TblUserRoles getRole() {
		return role;
	}

	public LocalDateTime getLastOtpVerifiedAt() {
		return lastOtpVerifiedAt;
	}

	public void setLastOtpVerifiedAt(LocalDateTime lastOtpVerifiedAt) {
		this.lastOtpVerifiedAt = lastOtpVerifiedAt;
	}

	public void setRole(TblUserRoles role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsSocialMediaLogin() {
		return isSocialMediaLogin;
	}

	public void setIsSocialMediaLogin(Boolean isSocialMediaLogin) {
		this.isSocialMediaLogin = isSocialMediaLogin;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSocialMediaProfile() {
		return socialMediaProfile;
	}

	public void setSocialMediaProfile(String socialMediaProfile) {
		this.socialMediaProfile = socialMediaProfile;
	}

	public String getSocialMediaAccessToken() {
		return socialMediaAccessToken;
	}

	public void setSocialMediaAccessToken(String socialMediaAccessToken) {
		this.socialMediaAccessToken = socialMediaAccessToken;
	}

	public Date getSocialMediaAccessExpiry() {
		return socialMediaAccessExpiry;
	}

	public void setSocialMediaAccessExpiry(Date socialMediaAccessExpiry) {
		this.socialMediaAccessExpiry = socialMediaAccessExpiry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public UserStatus getStatus() {
		return userStatus;
	}

	public void setStatus(UserStatus status) {
		this.userStatus = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}
}
