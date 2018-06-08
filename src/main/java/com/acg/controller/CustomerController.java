package com.acg.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acg.config.Configuration;
import com.acg.constants.ErrorCodes;
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
	Configuration configuration;
	@Autowired
	CustomerService customerService;
	private Facebook facebook;
	private ConnectionRepository connectionRepository;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get All Customers", notes = "Returns list of customers", response = org.springframework.http.ResponseEntity.class)
	public ResponseEntity<List<Customer>> getAllCustomers(HttpServletResponse res) {
		try {
			List<Customer> list = customerService.get();
			if (list.isEmpty())
				res.setStatus(HttpStatus.NOT_FOUND.value());
			res.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<>(list);
		} catch (CustomerException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR,
					new AcgError(e.getErrorCode(), e.getMessage()));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, new AcgError(
					configuration.serverPort + ErrorCodes.GENERIC_EXCEPTION.getErrorCode(), e.getMessage()));
		}
	}

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add Customer", notes = "Add Customer", response = org.springframework.http.ResponseEntity.class)
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer, HttpServletResponse res) {
		try {
			res.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<>(customerService.add(customer), HttpStatus.CREATED);
		} catch (CustomerException e) {
			res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR,
					new AcgError(e.getErrorCode(), e.getMessage()));
		} catch (Exception e) {
			res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, new AcgError(
					configuration.serverPort + ErrorCodes.GENERIC_EXCEPTION.getErrorCode(), e.getMessage()));
		}
	}
	
	@DeleteMapping(value = "/")
	@ApiOperation(value = "Delete All Customers", notes = "Delete All Customers", response = org.springframework.http.ResponseEntity.class)
	public ResponseEntity<?> delete(HttpServletResponse res) {
		try {
			res.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<>(customerService.delete(), HttpStatus.OK);
		} catch (CustomerException e) {
			res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR,
					new AcgError(e.getErrorCode(), e.getMessage()));
		} catch (Exception e) {
			res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, new AcgError(
					configuration.serverPort + ErrorCodes.GENERIC_EXCEPTION.getErrorCode(), e.getMessage()));
		}
	}

	@GetMapping("/fb")
	public String ff(Model model) {
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "redirect:/connect/facebook";
		}

		model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
		PagedList<Post> feed = facebook.feedOperations().getFeed();
		model.addAttribute("feed", feed);
		return "hello";
	}
}
