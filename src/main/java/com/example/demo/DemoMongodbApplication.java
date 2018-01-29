package com.example.demo;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.CustomerSequenceRepository;

@SpringBootApplication
public class DemoMongodbApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	CustomerSequenceRepository customerSequenceRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoMongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//repository.deleteAll();

		/*for (long i = 0; i <= 10000; i++)
			repository.save(new Customer("Bob" + i, "Smith" + i));
		*/
		System.out.println(repository.findByFirstName("Alice"));
		Customer c = new Customer();
		c.setId(new BigInteger("27987987478721748262062530982"));
		System.out.println(repository.findById(c.getId()));

	}

}
