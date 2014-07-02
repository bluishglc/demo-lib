package com.ibatis.jpetstore.persistence.sharding;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class Shard {
	
	private String id;
	
	private SqlMapClientTemplate sqlMapClientTemplate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	
}
