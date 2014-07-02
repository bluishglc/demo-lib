package com.ibatis.jpetstore.persistence.sqlmapdao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibatis.common.util.PaginatedList;
import com.ibatis.jpetstore.domain.Product;
import com.ibatis.jpetstore.persistence.iface.ProductDao;

public class ProductSqlMapDaoTest extends BaseSqlMapDaoTest {
	
	@Autowired
	private ProductDao productDao;

	@Test
	public void testGetProductListByCategory() {
		PaginatedList list = productDao.getProductListByCategory("1000");
		assertEquals(4, list.size());
	}

	@Test
	public void testGetProduct() {
		Product p116 = productDao.getProduct("116");
		assertNotNull(p116);
		assertEquals("Finch", p116.getName());
	}

	@Test
	public void testSearchProductList() {
		PaginatedList list = productDao.searchProductList("fish");
		for (Object object : list) {
			System.out.println(((Product)object).getName());
		}
		assertEquals(4, list.size());
	}

}
