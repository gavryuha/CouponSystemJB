package com.projectjb.couponsystemjb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.projectjb.couponsystemjb.beans.Company;
import com.projectjb.couponsystemjb.beans.Customer;
import com.projectjb.couponsystemjb.enums.ClientType;
import com.projectjb.couponsystemjb.repo.CompanyRepository;
import com.projectjb.couponsystemjb.repo.CustomerRepository;
import com.projectjb.couponsystemjb.service.AdminService;
import com.projectjb.couponsystemjb.service.CompanyService;
import com.projectjb.couponsystemjb.service.CustomerService;
import com.projectjb.couponsystemjb.service.Facade;



@Service
public class CouponSystem {
	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CompanyRepository companyRepository;
	
	public Facade login(String email, String password, ClientType type) throws Exception {
		
		switch (type) {
		case ADMIN:
			if (email.equals("admin") && password.equals("1234")) {
				AdminService adminService = ctx.getBean(AdminService.class);
				System.out.println("Welcome Admin! You're logged in system");
				return adminService;
			}
		case COMPANY:
			Company company = companyRepository.findCompanyByEmailAndPassword(email, password);
			if (company != null) {
				CompanyService companyService = ctx.getBean(CompanyService.class);
				companyService.setCompanyId(company.getId());
				System.out.println("Welcome " + company.getName() + " company! You're logged in system");
				return companyService;
			}
		case CUSTOMER:
			Customer customer = customerRepository.findCustomerByEmailAndPassword(email, password);
			if (customer != null) {
				CustomerService customerService = ctx.getBean(CustomerService.class);
				customerService.setCustomerId(customer.getId());
				System.out.println("Welcome customer " + customer.getFirstName() + "! You're logged in system");
				return customerService;
			}
		}
		throw new Exception("Client not found. Check your data");
	}
}
