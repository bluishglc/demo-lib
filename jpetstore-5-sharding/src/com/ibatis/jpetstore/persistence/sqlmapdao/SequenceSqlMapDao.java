package com.ibatis.jpetstore.persistence.sqlmapdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.jpetstore.domain.Sequence;
import com.ibatis.jpetstore.persistence.iface.SequenceDao;
import com.ibatis.jpetstore.persistence.sharding.Partition;
import com.ibatis.jpetstore.persistence.sharding.ShardLocator;

@Repository("sequenceDao")
public class SequenceSqlMapDao implements SequenceDao {
	
	@Autowired
	private ShardLocator shardLocator;

	/**
	 * This is a generic sequence ID generator that is based on a database table
	 * called 'SEQUENCE', which contains two columns (NAME, NEXTID).
	 * <p/>
	 * This approach should work with any database.
	 * 
	 * NOTE: This is not test ID generation solution for sharding database, please refer
	 * <a href="http://code.flickr.com/blog/2010/02/08/ticket-servers-distributed-unique-primary-keys-on-the-cheap/">Ticket Servers: Distributed Unique Primary Keys on the Cheap </a>
	 * 
	 * @param name
	 *            The name of the sequence.
	 * @return The Next ID @
	 */
	public synchronized int getNextId(String name) {
		Sequence sequence = new Sequence(name, -1);

		sequence = (Sequence) shardLocator.getShard(Partition.SEQUENCE).getSqlMapClientTemplate().queryForObject("getSequence", sequence);
		if (sequence == null) {
			throw new DaoException(
					"Error: A null sequence was returned from the database (could not get next "
							+ name + " sequence).");
		}
		Object parameterObject = new Sequence(name, sequence.getNextId() + 1);
		shardLocator.getShard(Partition.SEQUENCE).getSqlMapClientTemplate().update("updateSequence", parameterObject);

		return sequence.getNextId();
	}

}
