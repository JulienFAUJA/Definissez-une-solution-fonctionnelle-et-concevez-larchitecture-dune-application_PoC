package com.openclassrooms.yourcaryourwayapi.dto;

/**
 * The type Token dto.
 */
public class TokenDTO {

	private String token;

	/**
	 * Gets token.
	 *
	 * @return the token
	 */
	public String getToken() {
 		return token;
 	}

	/**
	 * Sets token.
	 *
	 * @param token the token
	 */
	public void setToken(String token) {
 		this.token = token;
 	}

	/**
	 * Instantiates a new Token dto.
	 */
	public TokenDTO() {
    	
    }

	/**
	 * Instantiates a new Token dto.
	 *
	 * @param token the token
	 */
	public TokenDTO(String token) {
 		this.token=token;
    	
    }
	
}
