package com.ibatis.jpetstore.service;

import com.ibatis.jpetstore.domain.Account;

public interface AccountService {

	Account getAccount(String username);

	Account getAccount(String username, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);

}