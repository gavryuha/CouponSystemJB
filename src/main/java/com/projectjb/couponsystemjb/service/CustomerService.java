package com.projectjb.couponsystemjb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectjb.couponsystemjb.beans.Coupon;
import com.projectjb.couponsystemjb.beans.Customer;
import com.projectjb.couponsystemjb.enums.CouponType;
import com.projectjb.couponsystemjb.repo.CouponRepository;
import com.projectjb.couponsystemjb.repo.CustomerRepository;

@Service
public class CustomerService implements Facade {
	@Autowired
	CouponRepository couponRepository;
	@Autowired
	CustomerRepository customerRepository;

	long customerId;
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	// Purchasing a single coupon.
	public boolean purchaseCoupon(long couponId) throws Exception {
		Coupon coupon = couponRepository.findById(couponId).get();
		try {
			if (!couponRepository.existsById(coupon.getId())) {
				throw new Exception("No coupon with such ID to purchase");
			} else if (coupon.getAmount() < 1) {
				throw new Exception("This coupon has ran out.");
			} else if (customerRepository.findById(customerId).get().getCoupons().contains(coupon)) {
				throw new Exception("Customer has aleady purchased this coupon");
			}

			Customer customer = customerRepository.findById(customerId).get();
			customer.getCoupons().add(coupon);
			customerRepository.save(customer);
			coupon.setAmount(coupon.getAmount() - 1);
			couponRepository.save(coupon);
			return true;

		} catch (Exception m) {
			throw new Exception("Failed to purchase coupon: " + m.getMessage());
		}
	}

	// All customer coupons.
	public List<Coupon> getCustomerCoupons() throws Exception {
		try {
			if (customerRepository.findById(customerId).get().getCoupons().isEmpty()
					|| customerRepository.findById(customerId).get().getCoupons() == null) {
				throw new Exception("Customr has no coupons.");
			}
			return customerRepository.findById(customerId).get().getCoupons();
		} catch (Exception e) {
			throw new Exception("Failed to get customers coupons: " + e.getMessage());
		}
	}

	// Customer coupons from specific category.
	public List<Coupon> getCustomerCoupons(String type) throws Exception {
		CouponType couponType = CouponType.valueOf(type);
		List<Coupon> tmpCoupons = new ArrayList<Coupon>();
		List<Coupon> coupons = customerRepository.findById(customerId).get().getCoupons();
		try {
			if (customerRepository.findById(customerId).get().getCoupons().isEmpty()) {
				throw new Exception("Customer has no coupons.");
			}
			for (Coupon c : coupons) {
				if (c.getCategory().equals(couponType)) {
					tmpCoupons.add(c);
				}
			}
			return tmpCoupons;
		} catch (Exception e) {
			throw new Exception("Failed to get customer coupons: " + e.getMessage());
		}
	}
	
	// Customer coupons with a maximum price.
		public List<Coupon> getCustomerCoupons(double maxPrice) throws Exception {
			List<Coupon> tmpCoupons = new ArrayList<Coupon>();
			try {
				if (customerRepository.findById(customerId).get().getCoupons().isEmpty()) {
					throw new Exception("Customr has no coupons.");
				}						
				for (Coupon c : customerRepository.findById(customerId).get().getCoupons()) {
					if (c.getPrice() <= maxPrice) {
						tmpCoupons.add(c);
					}
				}
				return tmpCoupons;
			} catch (Exception e) {
				throw new Exception("Failed to get customer coupons: " + e.getMessage());
			}
		}

		// Customer information.
		public Customer getCustomerDetails() {
			return customerRepository.findById(customerId).get();
		}
}
