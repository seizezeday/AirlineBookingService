package bionic.project.abo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bionic.project.abo.enums.Role;
import bionic.project.abo.enums.Status;


/**
 * 
 * @author Vasya
 *	Entity for autorization purposes
 */

@Entity
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String login;
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date registrationDate = new java.util.Date();
	private Role role;
	private Status status;
	
	
//	public enum Role {
//		SUPERADMIN, ADMIN, OPERATOR, ANALYTIC }
	
//	public enum Status {ACTIVE, BLOCKED}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public java.util.Date getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(java.util.Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
	

}
