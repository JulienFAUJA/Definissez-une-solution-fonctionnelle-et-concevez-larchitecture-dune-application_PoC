package com.openclassrooms.yourcaryourwayapi.dto;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * The type User register dto.
 */
public class UserRegisterDTO {

	
    @NotBlank(message="L'adresse email est obligatoire...")
    @Email(message = "Le format de l'adresse mail n'est pas valide...")
	private String email;
    
    @NotBlank(message="Le prénom est obligatoire")
	private String firstname;

	@NotBlank(message="Le nom est obligatoire")
	private String lastname;
    
    @NotBlank(message="Le mot de passe est obligatoire...")
	private String password;

	private Timestamp date_naissance;


	private Timestamp  created_at;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Timestamp getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Timestamp date_naissance) {
		this.date_naissance = date_naissance;
	}

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
	 * Gets created at.
	 *
	 * @return the created at
	 */
	public Timestamp getCreated_at() {
		return created_at;
	}

	/**
	 * Sets created at.
	 *
	 * @param created_at the created at
	 */
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	/**
	 * Sets created at now.
	 */
	public void setCreated_atNow() {
		Timestamp now = Timestamp.from(Instant.now());
		this.setCreated_at(now);
	}


	/**
	 * Instantiates a new User register dto.
	 */
	public UserRegisterDTO() {
	}


	/**
	 * Instantiates a new User register dto.
	 *
	 * @param email the email
	 */
	public UserRegisterDTO(String email) {
		this.email = email;

	}

	/**
	 * Instantiates a new User register dto.
	 *
	 * @param email    the email
	 * @param password the password
	 */
	public UserRegisterDTO(String email,String password) {
		this.email = email;
		this.password = password;
		Timestamp now = Timestamp.from(Instant.now());
		this.created_at = now;
	}
	
	
	@Override
	public String toString() {
		return "UserRegisterDTO [email=" + email +  ", password=" + password
				+ ", created_at=" + created_at + "]";
	}

	/**
	 * Instantiates a new User register dto.
	 *
	 * @param email      the email
	 * @param password   the password
	 * @param created_at the created at
	 */
	public UserRegisterDTO(String email, String password, Timestamp created_at) {
		this.email = email;
		this.password = password;
		this.created_at = created_at;
	}

}