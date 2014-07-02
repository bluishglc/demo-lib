package com.ibatis.jpetstore.service;

import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.ibatis.jpetstore.domain.Account;

public class ServiceFactory {

	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-resources.xml");
	
	public static AccountService getAccountService(){
		return (AccountService) context.getBean("accountService");
	}
	
	public static CatalogService getCatalogService(){
		return (CatalogService) context.getBean("catalogService");
	}
	
	public static OderService getOrderService(){
		return (OderService) context.getBean("orderService");
	}
	
	public static void main(String[] args) {
		Account a = getAccountService().getAccount("j2ee");
	}
}
