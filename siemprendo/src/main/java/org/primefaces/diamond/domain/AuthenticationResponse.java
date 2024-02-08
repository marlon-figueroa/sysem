package org.primefaces.diamond.domain;

import com.google.gson.annotations.SerializedName;

public class AuthenticationResponse {

	@SerializedName("access_token")
	private String accessToken;
	
	@SerializedName("refresh_token")
	private String refreshToken;

	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}