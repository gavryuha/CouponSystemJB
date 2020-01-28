package com.projectjb.couponsystemjb.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Coupons")
public class Coupon {
	private long id;
	private long companyId;
	private Category category;
	private String titleString;
	private String descriptionString;
	private Date startDate;
	private Date endDate;
	private long amount;
	private Double priceDouble;
	private String imageString;
	private Date issuedDate;
	private int status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	@Column(nullable = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Column(nullable = false)
	public String getTitleString() {
		return titleString;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}
	
	@Column(nullable = false)
	public String getDescriptionString() {
		return descriptionString;
	}

	public void setDescriptionString(String descriptionString) {
		this.descriptionString = descriptionString;
	}
	
	@Column(nullable = false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(nullable = false)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Column(nullable = true)
	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	@Column(nullable = false)
	public Double getPriceDouble() {
		return priceDouble;
	}

	public void setPriceDouble(Double priceDouble) {
		this.priceDouble = priceDouble;
	}
	
	@Column(nullable = true)
	public String getImageString() {
		return imageString;
	}

	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	
	@Column(nullable = false)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(nullable = false)
	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Coupon(long id, long companyId, Category category, String titleString, String descriptionString,
			Date startDate, Date endDate, long amount, Double priceDouble, String imageString) {
		this.id = id;
		this.companyId = companyId;
		this.category = category;
		this.titleString = titleString;
		this.descriptionString = descriptionString;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.priceDouble = priceDouble;
		this.imageString = imageString;
	}

	public Coupon() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyId=" + companyId + ", category=" + category + ", titleString="
				+ titleString + ", descriptionString=" + descriptionString + ", startDate=" + startDate + ", endDate="
				+ endDate + ", amount=" + amount + ", priceDouble=" + priceDouble + ", imageString=" + imageString
				+ ", status=" + status + "]";
	}

}
