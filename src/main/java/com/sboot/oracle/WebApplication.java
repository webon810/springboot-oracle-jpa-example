package com.sboot.oracle;

import com.sboot.oracle.dao.CustomerRepository;
import com.sboot.oracle.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.stream.Stream;

import javax.sql.DataSource;

import static java.lang.System.exit;

@SpringBootApplication
public class WebApplication implements CommandLineRunner {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Transactional(readOnly = true)
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Datasource = " + datasource);
		
		System.out.println("\n1.findAll()...");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
		
		System.out.println("\n1.findByEmail(String email)...");
        for (Customer customer : customerRepository.findByEmail("222@yahoo.com")) {
            System.out.println(customer);
        }
        
		System.out.println("\n1.findByDate(String date)...");
        for (Customer customer : customerRepository.findByDate(sdf.parse("2017-02-10"))) {
            System.out.println(customer);
        }
        
		System.out.println("\n1.findByEmail(String email)...");
        try (Stream<Customer> stream = customerRepository.findByEmailReturnStream("333@yahoo.com")){
        	stream.forEach(x -> System.out.println(x));
        }
		
		System.out.println("Done !");
		
		//this will drop the table after.
		//exit(0);
		
	}

}
