package com.app.dto;
public class ApiResponse {

    private boolean valid;
    private String username;
    private String token;

    

    public ApiResponse(boolean valid, String username) {
		this.valid = valid;
		this.username = username;
	}

	public ApiResponse(boolean valid, String username, String token) {
		this.valid = valid;
		this.username = username;
		this.token=token;
	}

	public boolean isValid() {
        return valid;
    }

    public String getUsername() {
        return username;
    }
    public String getToken() {
        return token;
    }
    
}
