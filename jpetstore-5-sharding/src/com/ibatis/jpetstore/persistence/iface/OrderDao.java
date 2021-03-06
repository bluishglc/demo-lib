package com.ibatis.jpetstore.persistence.iface;

import com.ibatis.common.util.PaginatedList;
import com.ibatis.jpetstore.domain.Order;

public interface OrderDao {

  PaginatedList getOrdersByUserId(String userId);

  Order getOrder(int orderId);

  void insertOrder(Order order);

}
