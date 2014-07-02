package net.sf.sharding.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.sf.sharding.model.MultiShardPartition;

@Repository
public class OrderDaoImpl implements OrderDao{
	
	private static final String orderQuery = "select * from orders where id=?";	
	private static final String saveOrder = "insert into orders (id,name) values (?,?)";
	
	@Autowired
	private MultiShardPartition orderPartition;

	@Override
	public Order get(Long id) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) orderPartition.getShard(id).getResource();
		RowMapper<Order> orderRowMapper = new RowMapper<Order>() {
			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order order = new Order();
				order.setId(rs.getLong("id"));
				order.setName(rs.getString("name"));
				return order;
			}
		};
		return jdbcTemplate.queryForObject(orderQuery, orderRowMapper,id);
	}

	@Override
	public void save(Order order) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) orderPartition.getShard(order.getId()).getResource();
		jdbcTemplate.update(saveOrder, order.getId(),order.getName());	
	}

}
