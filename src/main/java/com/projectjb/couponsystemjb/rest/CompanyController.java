package com.projectjb.couponsystemjb.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectjb.couponsystemjb.beans.Coupon;
import com.projectjb.couponsystemjb.service.CompanyService;



@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private Map<String, Session> tokensMap;

	private Session isActive(String token) {
		return tokensMap.get(token);
	}
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	CustomerController customerController;
	
	@PostMapping("/addCoupon/{token}")
	public ResponseEntity<?> addCompany(@RequestBody Coupon coupon, @PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(companyService.addCoupon(coupon), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to add Coupon.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/updateCoupon/{token}")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(companyService.updateCoupon(coupon), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to update Coupon.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("/deleteCoupon/{couponId}/{token}")
	public ResponseEntity<?> deleteCoupon(@PathVariable long couponId, @PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(companyService.deleteCoupon(couponId), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to delete Coupon.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getAllCoupons/{token}")
	public ResponseEntity<?> getAllCoupons(@PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(companyService.getAllCompanyCoupons(), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to display coupons ", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getCouponsByCategory/{type}/{token}")
	public ResponseEntity<?> getCouponsByCategory(@PathVariable String type,@PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(companyService.getCompanyCouponsByCategory(type), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to display coupons ", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getCouponsByMaxPrice/{price}/{token}")
	public ResponseEntity<?> getCouponsByMaxPrice(@PathVariable double price,@PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(companyService.getCompanyCouponsByMaxPrice(price), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to display coupons ", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getCompanyInformation/{token}")
	public ResponseEntity<?> getCompanyInformation(@PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(companyService.getCompanyDetails(), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to company information ", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
}
