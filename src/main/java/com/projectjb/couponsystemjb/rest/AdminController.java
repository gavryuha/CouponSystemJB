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

import com.projectjb.couponsystemjb.beans.Company;
import com.projectjb.couponsystemjb.beans.Customer;
import com.projectjb.couponsystemjb.service.AdminService;
import com.projectjb.couponsystemjb.service.CompanyService;
import com.projectjb.couponsystemjb.service.CustomerService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;

	@Autowired
	CompanyService companyService;

	@Autowired
	CustomerService customerService;

	@Autowired
	private Map<String, Session> tokensMap;

	private Session isActive(String token) {
		return tokensMap.get(token);
	}

	@PostMapping("/addCompany/{token}")
	public ResponseEntity<?> addCompany(@RequestBody Company company, @PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(adminService.addCompany(company), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to add Company.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/updateCompany/{token}")
	public ResponseEntity<?> updateCompany(@RequestBody Company company, @PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(adminService.updateCompany(company), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to update Company.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("/deleteCompany/{companyId}/{token}")
	public ResponseEntity<?> deleteCompany(@PathVariable long companyId, @PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(adminService.deleteCompany(companyId), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to delete Company.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/getAllCompanies/{token}")
	public ResponseEntity<?> getAllCompanies(@PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to display companies ", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/getOneCompany/{companyId}/{token}")
	public ResponseEntity<?> getOneCompany(@PathVariable long companyId, @PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(adminService.getOneCompany(companyId), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to find company ", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/addCustomer/{token}")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(adminService.addCustomer(customer), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to add Customer.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/updateCustomer/{token}")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(adminService.updateCustomer(customer), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to update Customer.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("/deleteCustomer/{customerId}/{token}")
	public ResponseEntity<?> deleteCustomer(@PathVariable long customerId, @PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(adminService.deleteCustomer(customerId), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to delete Customer.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/getAllCustomers/{token}")
	public ResponseEntity<?> getAllCustomers(@PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to display customers ", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/getOneCustomer/{customerId}/{token}")
	public ResponseEntity<?> getOneCustomer(@PathVariable long customerId, @PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<>(adminService.getOneCustomer(customerId), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to find customer ", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}
}
