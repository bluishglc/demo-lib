package com.threeti.umax.sample.composite.service.impl;

import org.apache.log4j.Logger;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeti.umax.sample.composite.service.FinancialRiskEvaluationManager;
import com.threeti.umax.sample.composite.service.FinancialRiskEvaluationService;
import com.threeti.umax.sample.financialmockapp.service.Credit;
import com.threeti.umax.sample.financialmockapp.service.CreditService;
import com.threeti.umax.sample.salesmockapp.service.Contract;


/**
 * Implementation of CreditManager interface.
 *
 * @author Laurence Geng
 */
@Service("financialRiskEvaluationManager")
@WebService(serviceName = "FinancialRiskEvaluationService", 
			endpointInterface = "com.threeti.umax.sample.composite.service.FinancialRiskEvaluationService", 
			targetNamespace="http://service.composite.sample.umax.threeti.com/")
public class FinancialRiskEvaluationManagerImpl implements FinancialRiskEvaluationService,FinancialRiskEvaluationManager {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FinancialRiskEvaluationManagerImpl.class);
	
	private CreditService creditService;
    
	/**
     * {@inheritDoc}
     */
	@Override
	public Boolean existCreditRisk(Contract contract) {
		Double contractAmount = contract.getAmount();
		String customerId = contract.getFirstParty().getId().toString();
		Credit credit = creditService.getCustomerCredit(customerId);
		Double crediteLine = credit.getCreditLine();
		Double debt = credit.getDebt();
		if(contractAmount+debt>crediteLine){
			logger.info("WARNING: There is credit risk for this contract. " +
			"The contract amount + debt > customer credit line!");
			return true;
		}else{
			logger.info("There is no credit risk for this contract.");
			return false;
		}
	}

	@Autowired
	public void setCreditService(CreditService creditService) {
		this.creditService = creditService;
	}

}
