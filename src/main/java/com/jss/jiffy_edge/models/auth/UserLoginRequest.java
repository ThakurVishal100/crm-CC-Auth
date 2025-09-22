package com.jss.jiffy_edge.models.auth;

public class UserLoginRequest {
	private String email;
	private String password;

	private String geoCity;
	private String geoCountry;
	private String geoState;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}