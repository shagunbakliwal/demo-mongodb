package com.acg.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.acg.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, BigInteger> {
    public List<Customer> findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
