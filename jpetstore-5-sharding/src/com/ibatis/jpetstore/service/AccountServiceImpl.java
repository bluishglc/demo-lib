package com.ibatis.jpetstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.jpetstore.domain.Account;
import com.ibatis.jpetstore.persistence.iface.AccountDao;
import com.ibatis.jpetstore.persistence.iface.DaoFactory;
import com.ibatis.jpetstore.persistence.DaoConfig;

@Transactional
@Service("accountService")
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountDao accountDao;

  public AccountServiceImpl() {
  
//    DaoManager daoMgr = DaoConfig.getDaoManager();
//    this.accountDao =DaoFactory.getAccountDao();
  }

  public AccountServiceImpl(AccountDao accountDao) {
    this.accountDao = accountDao;
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.AccountService#getAccount(java.lang.String)
 */
public Account getAccount(String username) {
    return accountDao.getAccount(username);
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.AccountService#getAccount(java.lang.String, java.lang.String)
 */
public Account getAccount(String username, String password) {
    return accountDao.getAccount(username, password);
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.AccountService#insertAccount(com.ibatis.jpetstore.domain.Account)
 */
public void insertAccount(Account account) {
    accountDao.insertAccount(account);
  }

  /* (non-Javadoc)
 * @see com.ibatis.jpetstore.service.AccountService#updateAccount(com.ibatis.jpetstore.domain.Account)
 */
public void updateAccount(Account account) {
    accountDao.updateAccount(account);
  }

}
