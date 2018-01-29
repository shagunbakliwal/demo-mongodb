package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.CustomerSequence;

public interface CustomerSequenceRepository extends MongoRepository<CustomerSequence, String> {

	CustomerSequence findTopById();
	
}
