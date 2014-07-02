package com.threeti.umax.sample.composite.service;


import com.threeti.umax.sample.financialmockapp.service.Credit;
import com.threeti.umax.sample.salesmockapp.service.Contract;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
public interface FinancialRiskEvaluationManager {
	
	/**
	 * Check whether there is credit risk.
	 * If contract amount + debt > credit line, result is true, else false.
	 *
	 * @param contract the contract
	 * @param credit the credit
	 * @return the boolean
	 */
	public Boolean existCreditRisk(Contract contract);
	
}
