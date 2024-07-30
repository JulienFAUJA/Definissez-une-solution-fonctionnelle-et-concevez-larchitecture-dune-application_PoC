package com.openclassrooms.yourcaryourwayapi.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * The type User login dto.
 */
public class UserLoginDTO {

	@NotBlank(message="L'adresse email est obligatoire...")
    @Email(message = "Le format de l'adresse mail n'est pas valide...")
	private String email;
	
	@NotBlank(message="Le mot de passe est obligatoire...")
	private String password;


	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * Instantiates a new User login dto.
	 *
	 * @param email    the email
	 * @param password the password
	 */
	public UserLoginDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	
	
	
	@Override
	public String toString() {
		return "UserLoginDTO [email=" + email + ", password=" + password+ "]";
	}


}