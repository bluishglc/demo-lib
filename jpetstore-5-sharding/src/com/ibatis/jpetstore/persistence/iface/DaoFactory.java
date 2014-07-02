package com.ibatis.jpetstore.persistence.iface;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoFactory {
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-resources.xml");
	
	public static AccountDao getAccountDao(){
		return (AccountDao)context.getBean("accountDao");
	}
	
	public static CategoryDao getCategoryDao(){
		return (CategoryDao)context.getBean("categoryDao");
	}
	
	public static ItemDao getItemDao(){
		return (ItemDao)context.getBean("itemDao");
	}
	
	public static OrderDao getOrderDao(){
		return (OrderDao)context.getBean("orderDao");
	}
	
	public static ProductDao getProductDao(){
		return (ProductDao)context.getBean("productDao");
	}
	
	public static SequenceDao gerSequenceDao(){
		return (SequenceDao)context.getBean("sequenceDao");
	}
	
	public static void main(String[] args) {
		getCategoryDao().getCategoryList();
	}
}
