package com.ibatis.jpetstore.persistence.sqlmapdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.common.util.PaginatedArrayList;
import com.ibatis.common.util.PaginatedList;
import com.ibatis.jpetstore.domain.LineItem;
import com.ibatis.jpetstore.domain.Order;
import com.ibatis.jpetstore.persistence.iface.OrderDao;
import com.ibatis.jpetstore.persistence.sharding.Partition;
import com.ibatis.jpetstore.persistence.sharding.Shard;
import com.ibatis.jpetstore.persistence.sharding.ShardLocator;

@Repository("orderDao")
public class OrderSqlMapDao implements OrderDao {

	@Autowired
	private ShardLocator shardLocator;

	public PaginatedList getOrdersByUserId(String username) {
		List<Shard> shards = shardLocator.getShards(Partition.ORDER);
		List<Order> orders = new ArrayList<Order>();
		for (Shard shard : shards) {
			orders.addAll(shard.getSqlMapClientTemplate().queryForList("getOrdersByUserId",username));
		}
		return new PaginatedArrayList(orders,10);
	}

	public Order getOrder(int orderId) {
		Shard shard = shardLocator.getShard(Partition.ORDER, new Long(orderId));
		Order order = null;
		Object parameterObject = new Integer(orderId);
		order = (Order) shard.getSqlMapClientTemplate().queryForObject("getOrder", parameterObject);
		order.setLineItems(shard.getSqlMapClientTemplate().queryForList("getLineItemsByOrderId",
				new Integer(order.getOrderId())));
		return order;
	}

	public void insertOrder(Order order) {
		Shard shard = shardLocator.getShard(Partition.ORDER, new Long(order.getOrderId()));
		shard.getSqlMapClientTemplate().insert("insertOrder", order);
		shard.getSqlMapClientTemplate().insert("insertOrderStatus", order);
		for (int i = 0; i < order.getLineItems().size(); i++) {
			LineItem lineItem = (LineItem) order.getLineItems().get(i);
			lineItem.setOrderId(order.getOrderId());
			shard.getSqlMapClientTemplate().insert("insertLineItem", lineItem);
		}
	}

}
