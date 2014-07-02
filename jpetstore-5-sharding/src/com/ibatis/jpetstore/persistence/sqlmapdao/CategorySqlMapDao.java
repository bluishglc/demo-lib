package com.ibatis.jpetstore.persistence.sqlmapdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.jpetstore.domain.Category;
import com.ibatis.jpetstore.persistence.iface.CategoryDao;
import com.ibatis.jpetstore.persistence.sharding.Partition;
import com.ibatis.jpetstore.persistence.sharding.ShardLocator;

@Repository("categoryDao")
public class CategorySqlMapDao implements CategoryDao {
	
	@Autowired
	private ShardLocator shardLocator;

	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	public List getCategoryList() {
		return shardLocator.getShard(Partition.PRODUCT).getSqlMapClientTemplate().queryForList("getCategoryList", null);
	}

	public Category getCategory(String categoryId) {
		return (Category) shardLocator.getShard(Partition.PRODUCT).getSqlMapClientTemplate().queryForObject("getCategory", categoryId);
	}

}
