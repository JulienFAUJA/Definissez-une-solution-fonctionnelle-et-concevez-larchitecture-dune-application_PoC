package com.openclassrooms.yourcaryourwayapi.dto;

import java.sql.Timestamp;


/**
 * The type User dto.
 */
public class UserDTO {
	
	private Integer id;
	private String email;
	private String name;
	private Timestamp  created_at;

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * Instantiates a new User dto.
	 */
	public UserDTO() {
	}


	/**
	 * Instantiates a new User dto.
	 *
	 * @param email the email
	 * @param name  the name
	 */
	public UserDTO(String email, String name) {
		this.email = email;
		this.name = name;
	}


	/**
	 * Instantiates a new User dto.
	 *
	 * @param id         the id
	 * @param email      the email
	 * @param name       the name
	 * @param created_at the created at
	 */
	public UserDTO(Integer id, String email, String name, Timestamp created_at) {
		this.id=id;
		this.email = email;
		this.name = name;
		this.created_at = created_at;
	}
	

}