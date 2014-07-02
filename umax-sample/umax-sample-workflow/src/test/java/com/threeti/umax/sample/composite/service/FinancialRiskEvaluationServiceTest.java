package com.threeti.umax.sample.composite.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.threeti.umax.sample.salesmockapp.service.Contract;
import com.threeti.umax.sample.salesmockapp.service.ContractService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class FinancialRiskEvaluationServiceTest {
	
	@Autowired
	private FinancialRiskEvaluationService financialRiskEvaluationService;
	
	@Autowired
	private ContractService contractService;

	@Test
	public void testExistCreditRisk() {
		Contract contract = contractService.getContract("-1");
		financialRiskEvaluationService.existCreditRisk(contract);
		assertNotNull(financialRiskEvaluationService);
	}

}
