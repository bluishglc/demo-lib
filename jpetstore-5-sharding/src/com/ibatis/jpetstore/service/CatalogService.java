package com.ibatis.jpetstore.service;

import java.util.List;

import com.ibatis.common.util.PaginatedList;
import com.ibatis.jpetstore.domain.Category;
import com.ibatis.jpetstore.domain.Item;
import com.ibatis.jpetstore.domain.Product;

public interface CatalogService {

	List getCategoryList();

	Category getCategory(String categoryId);

	Product getProduct(String productId);

	PaginatedList getProductListByCategory(String categoryId);

	PaginatedList searchProductList(String keywords);

	PaginatedList getItemListByProduct(String productId);

	Item getItem(String itemId);

	boolean isItemInStock(String itemId);

}