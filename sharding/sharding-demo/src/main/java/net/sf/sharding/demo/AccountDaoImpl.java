package net.sf.sharding.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.sharding.model.SingleShardPartition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{
	
	private static final String accountQuery = "select * from account where id=?";	
	private static final String saveAccount = "insert into account (id,name) values (?,?)";

	@Autowired
	private SingleShardPartition accountPartition;
	
	public Account get(Long id) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) accountPartition.getShard().getResource();
		RowMapper<Account> accountRowMapper = new RowMapper<Account>() {
			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account account = new Account();
				account.setId(rs.getLong("id"));
				account.setName(rs.getString("name"));
				return account;
			}
		};
		return jdbcTemplate.queryForObject(accountQuery, accountRowMapper,id);
	}
	
	public void save(Account account){
		JdbcTemplate jdbcTemplate = (JdbcTemplate) accountPartition.getShard().getResource();
		jdbcTemplate.update(saveAccount, account.getId(),account.getName());			
	}
}
