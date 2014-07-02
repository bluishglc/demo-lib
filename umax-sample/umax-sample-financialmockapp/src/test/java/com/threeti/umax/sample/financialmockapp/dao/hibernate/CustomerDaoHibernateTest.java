package com.threeti.umax.sample.financialmockapp.dao.hibernate;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeti.umax.sample.financialmockapp.dao.CustomerDao;
import com.threeti.umax.sample.financialmockapp.model.Customer;

public class CustomerDaoHibernateTest extends BaseDaoTestCase {
	@Autowired
	private CustomerDao dao;

	@Test
	public void testGet() {
		Customer customer = dao.get(-1L);
		assertNotNull(customer);
	}

}
