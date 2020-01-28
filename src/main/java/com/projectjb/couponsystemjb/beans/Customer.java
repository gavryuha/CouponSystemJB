package com.projectjb.couponsystemjb.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Customer {
	private long id;
	private String firstNameString;
	private String lastNameString;
	private String emailString;
	private String passwordString;
	private List<Coupon> coupons = new ArrayList<Coupon>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getFirstNameString() {
		return firstNameString;
	}

	public void setFirstNameString(String firstNameString) {
		this.firstNameString = firstNameString;
	}

	@Column(nullable = false)
	public String getLastNameString() {
		return lastNameString;
	}

	public void setLastNameString(String lastNameString) {
		this.lastNameString = lastNameString;
	}

	@Column(nullable = false)
	public String getEmailString() {
		return emailString;
	}

	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}

	@Column(nullable = false)
	public String getPasswordString() {
		return passwordString;
	}

	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}

	@OneToMany(cascade = CascadeType.PERSIST)
	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String firstNameString, String lastNameString, String emailString, String passwordString,
			List<Coupon> coupons) {
		this.firstNameString = firstNameString;
		this.lastNameString = lastNameString;
		this.emailString = emailString;
		this.passwordString = passwordString;
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstNameString=" + firstNameString + ", lastNameString=" + lastNameString
				+ ", coupons=" + coupons + "]";
	}

}
