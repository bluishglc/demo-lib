package net.sf.sharding.demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/sharding.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class AccountDaoImplTest {
	
	@Autowired
	private AccountDao accountDao;

	@Test
	public void testSave() {
		accountDao.save(new Account(1L,"tom"));
		Account accout = accountDao.get(1L);
		assertNotNull(accout);
	}

}
