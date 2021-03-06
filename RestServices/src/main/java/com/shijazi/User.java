package com.shijazi;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonView;

//@JsonIgnoreProperties(value = { "password" })
@XmlRootElement(name = "users")
public class User {

	private Long id;

	@JsonView(User.Views.Public.class)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private String email;

	private String firstname;

	private String lastname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}

	public User(Long id, String username, String password, String email, String firstname, String lastname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public User() {
		// super();
	}

	public static final class Views {
		// show only public data
		public interface Public {
		}

		// show public and internal data
		public interface Internal extends Public {
		}
	}

}
