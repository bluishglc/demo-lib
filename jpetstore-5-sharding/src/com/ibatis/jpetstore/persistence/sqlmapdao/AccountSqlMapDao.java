package com.ibatis.jpetstore.persistence.sqlmapdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.jpetstore.domain.Account;
import com.ibatis.jpetstore.persistence.iface.AccountDao;
import com.ibatis.jpetstore.persistence.sharding.Partition;
import com.ibatis.jpetstore.persistence.sharding.Shard;
import com.ibatis.jpetstore.persistence.sharding.ShardLocator;

@Repository("accountDao")
public class AccountSqlMapDao implements AccountDao {

	@Autowired
	private ShardLocator shardLocator;

	
	public Account getAccount(String username) {
		List<Shard> shards =shardLocator.getShards(Partition.ACCOUNT);
		for (Shard shard : shards) {
			Account account = (Account) shard.getSqlMapClientTemplate().queryForObject(
					"getAccountByUsername", username);
			if(account!=null){
				return account;
			}
		}
		return null;
	}

	public Account getAccount(String username, String password) {
		List<Shard> shards =shardLocator.getShards(Partition.ACCOUNT);
		for (Shard shard : shards) {
			Account account = new Account();
			account.setUsername(username);
			account.setPassword(password);
			Account selectedAccount = (Account) shard.getSqlMapClientTemplate().queryForObject("getAccountByUsernameAndPassword", account);
			if(selectedAccount!=null){
				return selectedAccount;
			}
		}
		return null;
	}

	public void insertAccount(Account account) {
		Shard shard = shardLocator.getShard(Partition.ACCOUNT, new Long(account.getId()));
		shard.getSqlMapClientTemplate().update("insertAccount", account);
		shard.getSqlMapClientTemplate().update("insertProfile", account);
	}

	public void updateAccount(Account account) {
		Shard shard = shardLocator.getShard(Partition.ACCOUNT, new Long(account.getId()));
		shard.getSqlMapClientTemplate().update("updateAccount", account);
		shard.getSqlMapClientTemplate().update("updateProfile", account);
	}

}
