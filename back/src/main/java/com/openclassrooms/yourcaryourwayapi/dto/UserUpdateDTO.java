package com.openclassrooms.yourcaryourwayapi.dto;


/**
 * The type User update dto.
 */
public class UserUpdateDTO {


	private String email;
	private String name;


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
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Instantiates a new User update dto.
	 */
	public UserUpdateDTO() {
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
	 * Instantiates a new User update dto.
	 *
	 * @param email the email
	 * @param name  the name
	 */
	public UserUpdateDTO(String email, String name) {
		this.email = email;
		this.name = name;
	}

	/**
	 * Instantiates a new User update dto.
	 *
	 * @param email    the email
	 * @param name     the name
	 * @param password the password
	 */
	public UserUpdateDTO(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserUpdateDTO [email=" + email + ", name=" + name + " ,password="+password+"]";
	}

}