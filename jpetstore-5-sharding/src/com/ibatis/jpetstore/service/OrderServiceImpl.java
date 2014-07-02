package com.ibatis.jpetstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibatis.common.util.PaginatedList;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.jpetstore.domain.LineItem;
import com.ibatis.jpetstore.domain.Order;
import com.ibatis.jpetstore.persistence.DaoConfig;
import com.ibatis.jpetstore.persistence.iface.DaoFactory;
import com.ibatis.jpetstore.persistence.iface.ItemDao;
import com.ibatis.jpetstore.persistence.iface.OrderDao;
import com.ibatis.jpetstore.persistence.iface.SequenceDao;

@Transactional
@Service("orderService")
public class OrderServiceImpl implements OderService{

//  private DaoManager daoManager;

  @Autowired
  private ItemDao itemDao;
  @Autowired
  private OrderDao orderDao;
  @Autowired
  private SequenceDao sequenceDao;

  public OrderServiceImpl() {
//    daoManager = DaoConfig.getDaoManager();
//    itemDao = DaoFactory.getItemDao();
//    sequenceDao = DaoFactory.gerSequenceDao();
//    orderDao = DaoFactory.getOrderDao();
  }

  public OrderServiceImpl(DaoManager daoManager, ItemDao itemDao, OrderDao orderDao, SequenceDao sequenceDao) {
//    this.daoManager = daoManager;
    this.itemDao = itemDao;
    this.orderDao = orderDao;
    this.sequenceDao = sequenceDao;
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.OderService#insertOrder(com.ibatis.jpetstore.domain.Order)
 */
public void insertOrder(Order order) {
//    try {
      // Get the next id within a separate transaction
      order.setOrderId(getNextId("ordernum"));

//      daoManager.startTransaction();

      itemDao.updateAllQuantitiesFromOrder(order);
      orderDao.insertOrder(order);

//      daoManager.commitTransaction();
//    } finally {
//      daoManager.endTransaction();
//    }
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.OderService#getOrder(int)
 */
public Order getOrder(int orderId) {
    Order order = null;
//
//    try {
//      daoManager.startTransaction();

      order = orderDao.getOrder(orderId);

      for (int i = 0; i < order.getLineItems().size(); i++) {
        LineItem lineItem = (LineItem) order.getLineItems().get(i);
        lineItem.setItem(itemDao.getItem(lineItem.getItemId()));
      }

//      daoManager.commitTransaction();
//    } finally {
//      daoManager.endTransaction();
//    }

    return order;
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.OderService#getOrdersByUsername(java.lang.String)
 */
public PaginatedList getOrdersByUsername(String username) {
    return orderDao.getOrdersByUserId(username);
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.OderService#getNextId(java.lang.String)
 */
public int getNextId(String key) {
    return sequenceDao.getNextId(key);
  }


}
