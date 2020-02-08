package com.projectjb.couponsystemjb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectjb.couponsystemjb.beans.Company;
import com.projectjb.couponsystemjb.beans.Coupon;
import com.projectjb.couponsystemjb.beans.Customer;
import com.projectjb.couponsystemjb.repo.CompanyRepository;
import com.projectjb.couponsystemjb.repo.CouponRepository;
import com.projectjb.couponsystemjb.repo.CustomerRepository;

@Service
public class AdminService implements Facade {
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CouponRepository couponRepository;
	
		// Creating,adding new company.
		public Company addCompany(Company company) throws Exception {
			try {
				if (companyRepository.existsCompanyByNameOrEmail(company.getName(), company.getEmail())) {
					throw new Exception("A company with such name or Email already exists.");
				} else {
					companyRepository.save(company);
				}
			} catch (Exception e) {
				throw new Exception("Failed to add a the company: " + e.getMessage());
			}
			return company;
		}
		
		// Updating company information.
		public Company updateCompany(Company company) throws Exception {
			try {
				if (companyRepository.existsById(company.getId())) {
					Company tmp = companyRepository.findById(company.getId()).get();
					tmp.setEmail(company.getEmail());
					tmp.setPassword(company.getPassword());
					companyRepository.save(tmp);
				} else {
					throw new Exception("No company with such ID exists. ");
				}
			} catch (Exception e) {
				throw new Exception("Failed to update company information: " + e.getMessage());
			}
			return company;
		}
		
		// Delete company and all it's coupons.
		public boolean deleteCompany(long companyId) throws Exception {
			try {
				if (companyRepository.existsById(companyId)) {
					Company tmp = companyRepository.findById(companyId).get();
					List<Coupon> coupons = tmp.getCoupons();
					for (Coupon c : coupons) {
						couponRepository.deleteById(c.getId());
					}
					companyRepository.deleteById(companyId);
				} else {
					throw new Exception("No company with such ID exists");
				}
			} catch (Exception e) {
				throw new Exception("Failed to delete company: " + e.getMessage());
			}
			return true;
		}
		
		// Get all companies.
		public List<Company> getAllCompanies() throws Exception {
			try {
				return (List<Company>) companyRepository.findAll();
			} catch (Exception e) {
				throw new Exception("Failed to get all companies: " + e.getMessage());
			}
		}
		
		// Get one company.
		public Optional<Company> getOneCompany(long companyId) throws Exception {
			try {
				if (companyRepository.existsById(companyId)) {
					return companyRepository.findById(companyId);
				} else {
					throw new Exception("No company with such id exists");
				}
			} catch (Exception e) {
				throw new Exception("Failed to get company information: " + e.getMessage());
			}
		}
		
		// Add one customer.
		public Customer addCustomer(Customer customer) throws Exception {
			try {
				if (customerRepository.existsCustomerByEmail(customer.getEmail())) {
					throw new Exception("Customer with such Email already exists ");
				} else {
					customerRepository.save(customer);
				}
			} catch (Exception e) {
				throw new Exception("Failed to add customer: " + e.getMessage());
			}
			return customer;
		}
		
		// Update customer information.
		public Customer updateCustomer(Customer customer) throws Exception {
			Customer temp = null;
			try {
				if (customerRepository.existsById(customer.getId())) {
					temp = customerRepository.findById(customer.getId()).get();
					customerRepository.save(temp);
				} else {
					throw new Exception("No customer with such ID exists. ");
				}
			} catch (Exception e) {
				throw new Exception("Failed to update customer information: " + e.getMessage());
			}
			return temp;
		}
		
		// Delete one customer.
		public boolean deleteCustomer(long customerId) throws Exception {
			try {
				if (customerRepository.existsById(customerId)) {
					customerRepository.deleteById(customerId);
				} else {
					throw new Exception("No customer with such ID");
				}
			} catch (Exception e) {
				throw new Exception("Failed to delete customer: " + e.getMessage());
			}
			return true;
		}
		
		// Get all customers.
		public List<Customer> getAllCustomers() throws Exception {
			try {
				return (List<Customer>) customerRepository.findAll();
			} catch (Exception e) {
				throw new Exception("Failed to get all customers: " + e.getMessage());
			}
		}
		
		// Get one customer.
		public Customer getOneCustomer(long customerId) throws Exception {
			try {
				if (customerRepository.existsById(customerId)) {
					return customerRepository.findById(customerId).get();
				} else {
					throw new Exception("No customer with such ID");
				}
			} catch (Exception e) {
				throw new Exception("Failed to get the customer: " + e.getMessage());
			}
		}

		public boolean doesCompanyExist(String email, String password) {
			return companyRepository.existsCompanyByEmailAndPassword(email, password);
		}
		
}
