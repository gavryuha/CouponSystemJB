package com.projectjb.couponsystemjb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectjb.couponsystemjb.beans.Company;
import com.projectjb.couponsystemjb.beans.Coupon;
import com.projectjb.couponsystemjb.enums.CouponType;
import com.projectjb.couponsystemjb.repo.CompanyRepository;
import com.projectjb.couponsystemjb.repo.CouponRepository;

@Service
public class CompanyService implements Facade{
	@Autowired
	CouponRepository couponRepository;
	@Autowired
	CompanyRepository companyRepository;
	
	long companyId;
	
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	// Add new coupon.
		public Company addCoupon(Coupon coupon) throws Exception {
			if (!companyRepository.existsById(this.companyId)) {
				throw new Exception("There is no company with such ID");
			} else {
				Company tempCompany = companyRepository.findById(this.companyId).get();
				for (Coupon c : tempCompany.getCoupons()) {
					if (c.getTitle().equals(coupon.getTitle())) {
						throw new Exception("Coupon with such title already exists in this company");
					}
				}
				
				coupon.setCompany(tempCompany);
				tempCompany.getCoupons().add(coupon);
				companyRepository.save(tempCompany);
				couponRepository.save(coupon);
				return tempCompany;
			}
		}

		// Update existing coupon.
		public String updateCoupon(Coupon coupon) throws Exception {
					
			try {
				if (couponRepository.existsCouponByIdAndCompany_Id(coupon.getId(),companyId)) {
					Coupon tempCoupon = couponRepository.findById(coupon.getId()).get();
					tempCoupon.setAmount(coupon.getAmount());
					tempCoupon.setCategory(coupon.getCategory());
					tempCoupon.setDescription(coupon.getDescription());
					tempCoupon.setTitle(coupon.getTitle());
					tempCoupon.setStartDate(coupon.getEndDate());
					tempCoupon.setEndDate(coupon.getEndDate());
					tempCoupon.setPrice(coupon.getPrice());
					tempCoupon.setImage(coupon.getImage());
					couponRepository.save(tempCoupon);
					return "Coupon was updated.";
				} else {
					throw new Exception("No coupon with such id to update");
				}
			} catch (Exception e) {		
				throw new Exception("Failed to update coupon");
			}
		}
		
		// Delete coupon.
		public String deleteCoupon(long couponId) throws Exception {
			Company tmpCompany = companyRepository.findById(companyId).get();	
			Coupon tmpCoupon = couponRepository.findById(couponId).get();
			try {
				if(tmpCompany.getCoupons().contains(tmpCoupon)) {
					couponRepository.delete(tmpCoupon);
					return "Great success, coupon deleted.";
				} else {
					throw new Exception("Your company has no cuch coupon.");
				}
			} catch (Exception e) {
				throw new Exception("Failed to delete coupon");
			}
		}
		
		// Get all company coupons.
		public List<Coupon> getCompanyCoupons() throws Exception {
			if (companyRepository.existsById(this.companyId)) {
				Company tmpCompany = companyRepository.findById(this.companyId).get();
				return tmpCompany.getCoupons();
			} else {
				throw new Exception("No company with such ID");
			}
		}
		
		// Get company coupons from specific category.
		public List<Coupon> getCompanyCouponsByCategory(String type) throws Exception {
			List<Coupon> tmpCoupons = new ArrayList<Coupon>();
			CouponType couponType = CouponType.valueOf(type);
			if (companyRepository.existsById(this.companyId)) {
				Company tmpCompany = companyRepository.findById(this.companyId).get();
				for (Coupon c : tmpCompany.getCoupons()) {
					if (c.getCategory() == couponType) {
						tmpCoupons.add(c);
					}
				}
			} else {
				throw new Exception("No company with such ID");
			}
			if (tmpCoupons.isEmpty()) {
				throw new Exception("This company has no coupons with such category");
			} else {
				return tmpCoupons;
			}
		}
		
		// Get company coupons with a max price.
		public List<Coupon> getCompanyCouponsByMaxPrice(double price) throws Exception {
			List<Coupon> tmpCoupons = new ArrayList<Coupon>();
			if (companyRepository.existsById(this.companyId)) {
				Company tmpCompany = companyRepository.findById(this.companyId).get();
				for (Coupon c : tmpCompany.getCoupons()) {
					if (c.getPrice() <= price) {
						tmpCoupons.add(c);
					}
				}
			} else {
				throw new Exception("No company with such ID");
			}
			if (tmpCoupons.isEmpty()) {
				throw new Exception("This company has no coupons under searched price.");
			} else {
				return tmpCoupons;
			}
		}
		
		
		// Get company information.
		public Optional<Company> getCompanyDetails() {
			return companyRepository.findById(this.companyId);
		}

		public List<Coupon> getAllCompanyCoupons() {
			Company tmpCompany = companyRepository.findById(companyId).get();
			return tmpCompany.getCoupons();
		}
}
