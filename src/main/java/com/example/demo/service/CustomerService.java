package com.example.demo.service;

import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public Customer add(Customer customer) {
		return customerRepository.save(customer);
	}

	public List<Customer> get() {
		return Lists.newArrayList(customerRepository.findAll());
	}
	
}
