package net.sf.sharding.demo;

public interface AccountDao {

	public Account get(Long id);
	
	public void save(Account account);
}
