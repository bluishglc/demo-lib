package com.ibatis.jpetstore.persistence.sqlmapdao;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.common.util.PaginatedArrayList;
import com.ibatis.common.util.PaginatedList;
import com.ibatis.jpetstore.domain.Product;
import com.ibatis.jpetstore.persistence.iface.ProductDao;
import com.ibatis.jpetstore.persistence.sharding.Partition;
import com.ibatis.jpetstore.persistence.sharding.ShardLocator;

@Repository("productDao")
public class ProductSqlMapDao implements ProductDao {

	@Autowired
	private ShardLocator shardLocator;

	/**
	 * NTOE: When sharding, I update spring from 2.5 to 3.0, the old method queryForPaginatedList is removed,
	 * but I don't want to realize pagination really, to avoid effect upper layers,
	 * I keep returning PaginatedList object which is not database-based pagination.
	 */
	public PaginatedList getProductListByCategory(String categoryId) {			
		List<Product>  products = shardLocator.getShard(Partition.PRODUCT).getSqlMapClientTemplate().queryForList("getProductListByCategory",new Integer(categoryId));
		return new PaginatedArrayList(products, 10);
	}

	public Product getProduct(String productId) {
		return (Product) shardLocator.getShard(Partition.PRODUCT).getSqlMapClientTemplate().queryForObject("getProduct", new Integer(productId));
	}

	public PaginatedList searchProductList(String keywords) {
		Object parameterObject = new ProductSearch(keywords);
//		return sqlMapClientTemplate.queryForPaginatedList("searchProductList",
//				parameterObject, 10);

		List<Product>  products = shardLocator.getShard(Partition.PRODUCT).getSqlMapClientTemplate().queryForList("searchProductList",parameterObject);
		return new PaginatedArrayList(products, 10);
	}

	/* Inner Classes */

	public static class ProductSearch {
		private List keywordList = new ArrayList();

		public ProductSearch(String keywords) {
			StringTokenizer splitter = new StringTokenizer(keywords, " ", false);
			while (splitter.hasMoreTokens()) {
				keywordList.add("%" + splitter.nextToken() + "%");
			}
		}

		public List getKeywordList() {
			return keywordList;
		}
	}

}
