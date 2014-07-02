package com.ibatis.jpetstore.persistence.sqlmapdao;

import com.ibatis.common.util.PaginatedArrayList;
import com.ibatis.common.util.PaginatedList;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.jpetstore.domain.Item;
import com.ibatis.jpetstore.domain.LineItem;
import com.ibatis.jpetstore.domain.Order;
import com.ibatis.jpetstore.persistence.iface.ItemDao;
import com.ibatis.jpetstore.persistence.sharding.Partition;
import com.ibatis.jpetstore.persistence.sharding.ShardLocator;
import com.ibatis.sqlmap.client.SqlMapClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("itemDao")
public class ItemSqlMapDao implements ItemDao {

	@Autowired
	private ShardLocator shardLocator;

	public void updateAllQuantitiesFromOrder(Order order) {
		for (int i = 0; i < order.getLineItems().size(); i++) {
			LineItem lineItem = (LineItem) order.getLineItems().get(i);
			String itemId = lineItem.getItemId();
			Integer increment = new Integer(lineItem.getQuantity());
			Map param = new HashMap(2);
			param.put("itemId", itemId);
			param.put("increment", increment);
			shardLocator.getShard(Partition.PRODUCT).getSqlMapClientTemplate().update("updateInventoryQuantity", param);
		}
	}

	public boolean isItemInStock(String itemId) {
		Integer i = (Integer) shardLocator.getShard(Partition.PRODUCT).getSqlMapClientTemplate().queryForObject("getInventoryQuantity",itemId);
		return (i != null && i.intValue() > 0);
	}

	public PaginatedList getItemListByProduct(String productId) {
		List<Item> items = shardLocator.getShard(Partition.PRODUCT).getSqlMapClientTemplate().queryForList("getItemListByProduct",new Integer(productId));
		return new PaginatedArrayList(items, 10);
	}

	public Item getItem(String itemId) {
		Integer i = (Integer) shardLocator.getShard(Partition.PRODUCT).getSqlMapClientTemplate().queryForObject("getInventoryQuantity",
				itemId);
		Item item = (Item) shardLocator.getShard(Partition.PRODUCT).getSqlMapClientTemplate().queryForObject("getItem", itemId);
		item.setQuantity(i.intValue());
		return item;
	}

}
