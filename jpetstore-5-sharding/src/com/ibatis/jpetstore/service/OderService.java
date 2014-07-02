package com.ibatis.jpetstore.service;

import com.ibatis.common.util.PaginatedList;
import com.ibatis.jpetstore.domain.Order;

public interface OderService {

	void insertOrder(Order order);

	Order getOrder(int orderId);

	PaginatedList getOrdersByUsername(String username);

	int getNextId(String key);

}