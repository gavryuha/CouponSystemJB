package com.projectjb.couponsystemjb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectjb.couponsystemjb.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	boolean existsCompanyByNameOrEmail(String name, String email);

	boolean existsCompanyByEmailAndPassword(String email, String password);

	Company findCompanyByEmailAndPassword(String email, String password);

	
	

}
