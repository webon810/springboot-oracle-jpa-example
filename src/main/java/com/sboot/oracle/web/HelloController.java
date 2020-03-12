package com.sboot.oracle.web;

import com.sboot.oracle.dao.CustomerRepository;
import com.sboot.oracle.model.Customer;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sboot.oracle.dao.CustomerRepository;

@RestController
public class HelloController {
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	CustomerRepository customerrepository;
	
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String HelloHome() {
		return "Hi, here is to test the oracle connection";
	}
	
	@RequestMapping(value="/customer",method=RequestMethod.GET)
	public Iterable<Customer> HelloCustomer() {
	return customerrepository.findAll();
	}

}
