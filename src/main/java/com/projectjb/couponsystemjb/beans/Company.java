package com.projectjb.couponsystemjb.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {

	private long id;
	private String nameString;
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
	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
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

	public Company() {
		// TODO Auto-generated constructor stub
	}

	public Company(String nameString, String emailString, String passwordString, List<Coupon> coupons) {
		this.nameString = nameString;
		this.emailString = emailString;
		this.passwordString = passwordString;
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", nameString=" + nameString + ", coupons=" + coupons + "]";
	}

}
