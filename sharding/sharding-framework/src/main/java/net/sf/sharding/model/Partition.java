package net.sf.sharding.model;


/**
 * The Partition is the result of vertical sharding. Usually, vertical sharding will pick closely related tables as a partition.
 * If the data volume of tables in a partition is not huge, just one shard can afford them, then this partition will be
 * a {@link SingleShardPartition} which contains a {@link SingleShard}. Otherwise, we need keep to do horizontal sharding which
 * divide data into multiple shards. We call this kind of partition {@link MultiShardPartition}    
 * 
 * @author laurence.geng
 */
public abstract class Partition {

	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
