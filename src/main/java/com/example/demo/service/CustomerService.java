package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.CustomerSequenceRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerSequenceRepository customerSequenceRepository;

	@Autowired
	CustomerRepository customerRepository;

	public void add(Customer customer) {
		//customerRepository.save(new Customer(Long.parseLong(customerSequenceRepository.findTopById().getId() + 1),"Shagun", "Bakliwal"));
	}
	
}
