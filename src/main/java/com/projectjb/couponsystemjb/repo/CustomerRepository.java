package com.projectjb.couponsystemjb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectjb.couponsystemjb.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	boolean existsCustomerByEmail(String email);

	Customer findCustomerByEmailAndPassword(String email, String password);



}
