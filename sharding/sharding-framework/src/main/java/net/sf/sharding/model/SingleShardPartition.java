package net.sf.sharding.model;

import org.apache.log4j.Logger;

/**
 * A SingleShardPartition only contains those tables have no large data. 
 * It has only one Shard.The route rule of SingleShardPartition is very simple: 
 * Locate the partition then get the only shard derectly.
 * 
 * @author laurence.geng
 * @see Partition
 */
public class SingleShardPartition extends Partition {

	private static final Logger logger = Logger.getLogger(SingleShardPartition.class);

	private Shard shard;

	public Shard getShard() {
		if(logger.isDebugEnabled()){
			logger.debug("Shard Route: ["+id+"] => ["+ shard.getId()+"]");
		}
		return shard;
	}

	public void setShard(Shard shard) {
		this.shard = shard;
	}
	
}
