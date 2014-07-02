package com.threeti.umax.sample.financialmockapp.service;

import java.util.List;

import com.threeti.umax.sample.financialmockapp.model.Credit;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author Laurence Geng 
 */
public interface CreditManager extends GenericManager<Credit, Long> {
	
	Credit getCustomerCredit(String customerId);
	
	Credit getCredit(String creditId);
	
	List<Credit> getCredits();
	
	Credit saveCredit(Credit credit);
}
