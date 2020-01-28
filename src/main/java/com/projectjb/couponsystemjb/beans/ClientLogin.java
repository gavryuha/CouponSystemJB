package com.projectjb.couponsystemjb.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "logins")
public class ClientLogin {
	private String nameString;
	private String typeString;
	private String tokenString;
	private String tokenStatusString;
	private Date loginDate;

	@Column(nullable = false)
	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	@Column(nullable = false)
	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}

	@Column(nullable = false)
	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}

	@Column(nullable = false)
	public String getTokenStatusString() {
		return tokenStatusString;
	}

	public void setTokenStatusString(String tokenStatusString) {
		this.tokenStatusString = tokenStatusString;
	}

	@Column(nullable = false)
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public ClientLogin() {
		// TODO Auto-generated constructor stub
	}

	public ClientLogin(String nameString, String typeString, String tokenString, String tokenStatusString,
			Date loginDate) {
		this.nameString = nameString;
		this.typeString = typeString;
		this.tokenString = tokenString;
		this.tokenStatusString = tokenStatusString;
		this.loginDate = loginDate;
	}

}
