package com.ibatis.jpetstore.persistence.sqlmapdao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibatis.common.util.PaginatedList;
import com.ibatis.jpetstore.domain.Order;
import com.ibatis.jpetstore.persistence.iface.OrderDao;

public class OrderSqlMapDaoTest extends BaseSqlMapDaoTest {
	
	@Autowired
	private OrderDao orderDao;

	@Test
	public void testGetOrdersByUserId() {
		PaginatedList orders = orderDao.getOrdersByUserId("1000");
		assertSame(2, orders.size());
	}

	@Test
	public void testGetOrder() {
		assertNotNull(orderDao.getOrder(10000));
		assertNotNull(orderDao.getOrder(10001));
	}

	@Test
	public void testInsertOrder() {
		
		Order order0 = new Order();
		order0.setOrderId(10010);
		order0.setUserId(1000);
		order0.setOrderDate(new Date());
		order0.setShipToFirstName("jim");
		order0.setShipToLastName("green");
		order0.setShipAddress1("beijing-zhongguancun");
		order0.setShipAddress1("shanghai-century park");
		order0.setShipCity("Beijing");
		order0.setShipState("OK");
		order0.setShipZip("250014");
		order0.setShipCountry("China");
		order0.setBillToFirstName("jim");
		order0.setBillToLastName("green");
		order0.setBillAddress1("beijing-zhongguancun");
		order0.setBillAddress2("shanghai-century park");
		order0.setBillCity("Beijing");
		order0.setBillZip("250014");
		order0.setBillCountry("China");
		order0.setBillState("OK");
		order0.setTotalPrice(new BigDecimal(1100));
		order0.setCreditCard("999 9999 9999 9999");
		order0.setExpiryDate("12/03");
		order0.setCardType("Visa");
		order0.setCourier("UPS");
		order0.setLocale("CA");
		order0.setStatus("P");
		orderDao.insertOrder(order0);
		
		assertNotNull(orderDao.getOrdersByUserId("10010"));
		
		Order order1 = new Order();
		order1.setOrderId(10011);
		order1.setUserId(1001);
		order1.setOrderDate(new Date());
		order1.setShipToFirstName("jim");
		order1.setShipToLastName("green");
		order1.setShipAddress1("beijing-zhongguancun");
		order1.setShipAddress1("shanghai-century park");
		order1.setShipCity("Beijing");
		order1.setShipState("OK");
		order1.setShipZip("250014");
		order1.setShipCountry("China");
		order1.setBillToFirstName("jim");
		order1.setBillToLastName("green");
		order1.setBillAddress1("beijing-zhongguancun");
		order1.setBillAddress2("shanghai-century park");
		order1.setBillCity("Beijing");
		order1.setBillZip("250014");
		order1.setBillCountry("China");
		order1.setBillState("OK");
		order1.setTotalPrice(new BigDecimal(1100));
		order1.setCreditCard("999 9999 9999 9999");
		order1.setExpiryDate("12/03");
		order1.setCardType("Visa");
		order1.setCourier("UPS");
		order1.setLocale("CA");
		order1.setStatus("P");
		orderDao.insertOrder(order1);
		
		assertNotNull(orderDao.getOrdersByUserId("10011"));
	}

}
