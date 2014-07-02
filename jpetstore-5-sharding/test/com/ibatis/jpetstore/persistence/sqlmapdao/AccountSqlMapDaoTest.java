package com.ibatis.jpetstore.persistence.sqlmapdao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ibatis.jpetstore.domain.Account;
import com.ibatis.jpetstore.persistence.iface.AccountDao;

public class AccountSqlMapDaoTest extends BaseSqlMapDaoTest {
	
	@Autowired
	private AccountDao accountDao;

	@Test
	public void testGetAccountString() {
		Account account = accountDao.getAccount("j2ee");
		assertNotNull(account);
		assertEquals(1000, account.getId());
	}

	@Test
	public void testGetAccountStringString() {
		Account account = accountDao.getAccount("ACID","ACID");
		assertNotNull(account);
		assertEquals(1001, account.getId());
	}

	@Test
	public void testInsertAccount() {
		Account account1 = new Account();
		account1.setId(1002);
		account1.setUsername("abc");
		account1.setPassword("abc");
		account1.setAddress1("address1");
		account1.setAddress2("address2");
		account1.setFirstName("aaa");
		account1.setLastName("bbb");
		account1.setEmail("abc@163.com");
		account1.setCity("Beijing");
		account1.setStatus("OK");
		account1.setCountry("US");
		account1.setState("CA");
		account1.setZip("234343");
		account1.setPhone("234234234");
		account1.setLanguagePreference("chinese");
		account1.setFavouriteCategoryId("BIRDS");
		account1.setListOption(false);
		account1.setBannerOption(true);
		
		accountDao.insertAccount(account1);
		
		Account selectedAccount1 = accountDao.getAccount("abc","abc");
		assertNotNull(selectedAccount1);
		assertEquals(1002, selectedAccount1.getId());
		
		Account account2 = new Account();
		account2.setId(1003);
		account2.setUsername("bca");
		account2.setPassword("bca");
		account2.setAddress1("address1");
		account2.setAddress2("address2");
		account2.setFirstName("xxx");
		account2.setLastName("yyy");
		account2.setEmail("cba@163.com");
		account2.setCity("Beijing");
		account2.setStatus("OK");
		account2.setCountry("US");
		account2.setState("CA");
		account2.setZip("234343");
		account2.setPhone("234234234");
		account2.setLanguagePreference("chinese");
		account2.setFavouriteCategoryId("BIRDS");
		account2.setListOption(false);
		account2.setBannerOption(true);
		
		accountDao.insertAccount(account2);
		
		Account selectedAccount2 = accountDao.getAccount("bca","bca");
		assertNotNull(selectedAccount2);
		assertEquals(1003, selectedAccount2.getId());
	}

//	@Test
//	public void testUpdateAccount() {
//		fail("Not yet implemented");
//	}

}
