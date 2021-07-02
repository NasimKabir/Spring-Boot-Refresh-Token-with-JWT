package com.spring.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class TokenRefreshResponse {
	private String username;
	private String email;
	private String roles;
	private String accessToken;
	private String refreshToken;
	private String tokenType = "Bearer";

	public TokenRefreshResponse(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public TokenRefreshResponse(String token, String requestRefreshToken, String username, String toString) {
		this.accessToken = token;
		this.refreshToken = requestRefreshToken;
		this.username = username;
		this.roles = toString;
	}
}
