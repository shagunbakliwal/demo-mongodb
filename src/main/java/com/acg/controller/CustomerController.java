package com.acg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acg.domain.Customer;
import com.acg.exception.CustomerException;
import com.acg.service.CustomerService;
import com.acg.shareable.error.AcgError;
import com.acg.shareable.web.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	@GetMapping("/")
	@ApiOperation(value = "Get All Customers", notes = "Returns list of customers", response = org.springframework.http.ResponseEntity.class)
	public ResponseEntity<List<Customer>> getAllCustomers() {
		try {
			return new ResponseEntity<>(customerService.get());
		} catch (CustomerException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR,
					new AcgError(e.getErrorCode(), e.getMessage()));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, new AcgError(1, e.getMessage()));
		}
	}

	@PostMapping("/")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		try {
			return new ResponseEntity<>(customerService.add(customer), HttpStatus.CREATED);
		} catch (CustomerException e) {
			return new ResponseEntity<>(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, new AcgError(1, e.getMessage()));
		}
	}
}
