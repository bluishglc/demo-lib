package com.threeti.umax.sample.salesmockapp.service;

import java.util.List;

import com.threeti.umax.sample.salesmockapp.model.Contract;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author Laurence Geng 
 */
public interface ContractManager extends GenericManager<Contract, Long> {
	
	Contract getContract(String contractId);
	
	void setContractState(String contractId, String state);
	
	List<Contract> getContracts();
	
	Contract saveContract(Contract contract);
}
