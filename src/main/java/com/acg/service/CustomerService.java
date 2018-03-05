package com.acg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg.constants.Constants;
import com.acg.domain.Customer;
import com.acg.exception.CustomerException;
import com.acg.repository.CustomerRepository;
import com.google.common.collect.Lists;

@Service
public class CustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	CustomerRepository customerRepository;

	public Customer add(Customer customer) throws CustomerException {
		try {
			return customerRepository.save(customer);
		} catch (org.springframework.dao.DuplicateKeyException e) {
			throw new CustomerException(Constants.EMAIL_DUPLICATION.getErrorCode(),
					Constants.EMAIL_DUPLICATION.getValue());
		}

	}

	public List<Customer> get() throws CustomerException {
		try {
			return Lists.newArrayList(customerRepository.findAll());
		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		}

	}

}
