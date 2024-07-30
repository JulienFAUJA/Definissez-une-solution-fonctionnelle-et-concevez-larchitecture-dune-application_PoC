package com.openclassrooms.yourcaryourwayapi.models;

import java.security.Principal;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * The type User model.
 */
@Entity
@Table(name = "Utilisateur")
public class UserModel implements Principal{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "utilisateur_id")
	private Integer id;
	@Column(unique=true, name = "utilisateur_email")
	private String email;
	@Column(name = "utilisateur_prenom")
	private String firstname;
	@Column(name = "utilisateur_nom")
	private String lastname;
	@Column(name = "utilisateur_motdepasse")
	private String password;
	@Column(name = "utilisateur_date_naissance")
	private Timestamp date_naissance;
	@Column(name = "utilisateur_is_stripe_registered")
	private Boolean is_stripe_registered;
	@CreationTimestamp
	@Column(name = "utilisateur_date_creation_compte")
	private Timestamp  created_at;
	@Column(name = "adresse_id")
	private Integer adresse_id;


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

	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets Firstname.
	 *
	 * @param firstname the Firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
	 * Instantiates a new User model.
	 */
	public UserModel() {
		
	}
	
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", email=" + email + ", Firstname=" + firstname + ", password=" + password
				+ ", created_at=" + created_at;
	}

	@Override
	public String getName() {
		return firstname+" "+lastname;
	}

	/**
	 * Instantiates a new User model.
	 *
	 * @param email    the email
	 * @param password the password
	 */
	public UserModel(String email, String password) {
		this.email = email;
		this.password = password;
	}

	/**
	 * Instantiates a new User model.
	 *
	 * @param email    the email
	 * @param name     the name
	 * @param password the password
	 */
	public UserModel(String email, String name, String password) {
		this.email = email;
		this.firstname = name;
		this.password = password;
	}

	/**
	 * Instantiates a new User model.
	 *
	 * @param id         the id
	 * @param email      the email
	 * @param name       the name
	 * @param password   the password
	 * @param created_at the created at
	 */
	public UserModel(Integer id, String email, String name, String password, Timestamp created_at) {
		this.id=id;
		this.email = email;
		this.firstname = name;
		this.password = password;
		this.created_at = created_at;
	}

	public UserModel(Integer id, String email, String firstname, String lastname, String password, Timestamp created_at) {
		this.id=id;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.created_at = created_at;
	}

	public UserModel(String email, String firstname, String lastname, String password,Timestamp datenaissance, Timestamp created_at) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.date_naissance=datenaissance;
		this.is_stripe_registered=false;
		this.adresse_id=1;
		this.created_at = created_at;
	}

}
