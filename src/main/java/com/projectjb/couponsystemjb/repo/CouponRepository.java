package com.projectjb.couponsystemjb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectjb.couponsystemjb.beans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{

	boolean existsCouponByIdAndCompany_Id(long id, long companyId);

	

	

}
