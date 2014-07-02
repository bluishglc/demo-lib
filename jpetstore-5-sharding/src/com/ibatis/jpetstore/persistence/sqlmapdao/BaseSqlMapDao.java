package com.ibatis.jpetstore.persistence.sqlmapdao;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class BaseSqlMapDao extends SqlMapClientTemplate {

  protected static final int PAGE_SIZE = 4;

//  public BaseSqlMapDao(DaoManager daoManager) {
//    super(daoManager);
//  }

}
