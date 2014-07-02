package com.threeti.umax.sample.salesmockapp.service;

import java.util.List;

import com.threeti.umax.sample.salesmockapp.model.Customer;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author Laurence Geng 
 */
public interface CustomerManager extends GenericManager<Customer, Long> {
	
	Customer getCustomer(String customerId);
	
	List<Customer> getCustomers();
	
	Customer saveCustomer(Customer customer);
}
