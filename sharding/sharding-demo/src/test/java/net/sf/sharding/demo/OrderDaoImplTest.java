package net.sf.sharding.demo;

import static org.junit.Assert.*;

import net.sf.sharding.model.Shard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/sharding.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class OrderDaoImplTest {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private Shard orderShard0;
	
	@Autowired
	private Shard orderShard1;
	
	@Autowired
	private Shard orderShard2;
	
	@Autowired
	private Shard orderShard3;
	
	@Autowired
	private Shard orderShard4;

	@Test
	public void testSave() {
		orderDao.save(new Order(1L,"order_1"));
		Order order = orderDao.get(1L);
		assertNotNull(order);
	}
	
	@Test
	public void testMultiShardRoute() {
		
		for (long i = 0; i <= 21; i++) {
			orderDao.save(new Order(i, "order_" + i));
		}
		
		// orderShard0 should contains 5 records (id): 0,2,4,6,8
		JdbcTemplate jdbcTemplate0 = (JdbcTemplate) orderShard0.getResource();
		assertEquals(5,jdbcTemplate0.queryForInt("select count(*) from orders"));
		
		// orderShard1 should contains 5 records (id): 1,3,5,7,9
		JdbcTemplate jdbcTemplate1 = (JdbcTemplate) orderShard1.getResource();
		assertEquals(5,jdbcTemplate1.queryForInt("select count(*) from orders"));
		
		// orderShard2 should contains 2 records (id): 12,18
		JdbcTemplate jdbcTemplate2 = (JdbcTemplate) orderShard2.getResource();
		assertEquals(2,jdbcTemplate2.queryForInt("select count(*) from orders"));
		
		// orderShard3 should contains 4 records (id): 13,14,19,20
		JdbcTemplate jdbcTemplate3 = (JdbcTemplate) orderShard3.getResource();
		assertEquals(4,jdbcTemplate3.queryForInt("select count(*) from orders"));
		
		// orderShard4 should contains 6 records (id): 10,11,15,16,17,21
		JdbcTemplate jdbcTemplate4 = (JdbcTemplate) orderShard4.getResource();
		assertEquals(6,jdbcTemplate4.queryForInt("select count(*) from orders"));
	}

}
