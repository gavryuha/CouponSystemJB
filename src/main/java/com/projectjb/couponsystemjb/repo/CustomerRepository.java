package com.projectjb.couponsystemjb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectjb.couponsystemjb.beans.Coupon;

public interface CustomerRepository extends JpaRepository<Coupon, Long>{

}
