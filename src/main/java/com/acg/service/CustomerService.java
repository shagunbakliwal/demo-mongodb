package com.acg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg.config.Configuration;
import com.acg.constants.ErrorCodes;
import com.acg.domain.Customer;
import com.acg.exception.CustomerException;
import com.acg.repository.CustomerRepository;
import com.google.common.collect.Lists;

@Service
public class CustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	Configuration configuration;
	@Autowired
	CustomerRepository customerRepository;

	public Customer add(Customer customer) throws CustomerException {
		try {
			return customerRepository.insert(customer);
		} catch (org.springframework.dao.DuplicateKeyException e) {
			logger.error(e.getMessage(), e);
			throw new CustomerException(configuration.serverPort + ErrorCodes.EMAIL_DUPLICATION.getErrorCode(),
					e.getCause(), ErrorCodes.EMAIL_DUPLICATION.getValue());
		}

	}

	public List<Customer> get() throws CustomerException {
		try {
			return Lists.newArrayList(customerRepository.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CustomerException(configuration.serverPort + ErrorCodes.GENERIC_EXCEPTION.getErrorCode(),
					e.getCause(), ErrorCodes.GENERIC_EXCEPTION.getValue());
		}
	}

	public boolean delete() throws CustomerException {
		try {
			customerRepository.deleteAll();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CustomerException(configuration.serverPort + ErrorCodes.GENERIC_EXCEPTION.getErrorCode(),
					e.getCause(), e.getMessage());
		}
	}
}
