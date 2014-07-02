package com.threeti.umax.sample.financialmockapp.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class CreditServiceTest {
	
	@Autowired
	private CreditService creditService;

	@Test
	public void testGetCredits() {
//		creditService.getCredits();
		Credit c = creditService.getCredit("-1");
		assertNotNull(c);
	}

//	@Test
//	public void testSaveCredit() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCredit() {
//		fail("Not yet implemented");
//	}

}
