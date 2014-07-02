package com.ibatis.jpetstore.service;

import com.ibatis.common.util.PaginatedList;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.jpetstore.domain.Category;
import com.ibatis.jpetstore.domain.Item;
import com.ibatis.jpetstore.domain.Product;
import com.ibatis.jpetstore.persistence.iface.CategoryDao;
import com.ibatis.jpetstore.persistence.iface.DaoFactory;
import com.ibatis.jpetstore.persistence.iface.ItemDao;
import com.ibatis.jpetstore.persistence.iface.ProductDao;
import com.ibatis.jpetstore.persistence.DaoConfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

  @Autowired
  private CategoryDao categoryDao;
  @Autowired
  private ItemDao itemDao;
  @Autowired
  private ProductDao productDao;

  public CatalogServiceImpl() {
    //DaoManager daoManager = DaoConfig.getDaoManager();
//    categoryDao = DaoFactory.getCategoryDao();
//    productDao = DaoFactory.getProductDao();
//    itemDao = DaoFactory.getItemDao();
  }

  public CatalogServiceImpl(CategoryDao categoryDao, ItemDao itemDao, ProductDao productDao) {
    this.categoryDao = categoryDao;
    this.itemDao = itemDao;
    this.productDao = productDao;
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.CatalogService#getCategoryList()
 */
public List getCategoryList() {
    return categoryDao.getCategoryList();
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.CatalogService#getCategory(java.lang.String)
 */
public Category getCategory(String categoryId) {
    return categoryDao.getCategory(categoryId);
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.CatalogService#getProduct(java.lang.String)
 */
public Product getProduct(String productId) {
    return productDao.getProduct(productId);
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.CatalogService#getProductListByCategory(java.lang.String)
 */
public PaginatedList getProductListByCategory(String categoryId) {
    return productDao.getProductListByCategory(categoryId);
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.CatalogService#searchProductList(java.lang.String)
 */
public PaginatedList searchProductList(String keywords) {
    return productDao.searchProductList(keywords);
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.CatalogService#getItemListByProduct(java.lang.String)
 */
public PaginatedList getItemListByProduct(String productId) {
    return itemDao.getItemListByProduct(productId);
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.CatalogService#getItem(java.lang.String)
 */
public Item getItem(String itemId) {
    return itemDao.getItem(itemId);
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.CatalogService#isItemInStock(java.lang.String)
 */
public boolean isItemInStock(String itemId) {
    return itemDao.isItemInStock(itemId);
  }

}