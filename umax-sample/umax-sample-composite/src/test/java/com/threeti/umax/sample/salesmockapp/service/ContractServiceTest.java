package com.threeti.umax.sample.salesmockapp.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class ContractServiceTest {
	
	@Autowired
	private ContractService contractService;

//	@Test
//	public void testSaveContract() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetContracts() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetContract() {
		Contract contract = contractService.getContract("-1");
		assertNotNull(contract);
	}

}
