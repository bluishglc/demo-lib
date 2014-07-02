package net.sf.sharding.model;

/**
 * Each shard stands for a database. The resource object perhaps is a jdbc connection, a spring jdbc template,
 * a hibernate session, a spring hibernate template and so on, anyway, the client of Shard (i.e. DAO) will use
 * the reource object to r/w database.
 * <p>
 * The normal Shard is used for {@link SingleShardPartition} which has only one Shard. For {@link MultiShardPartition},
 * There is other type shard:{@link GroupShard}: The shard belongs to a certain {@link ShardGroup} which usually has many shards. 
 * The route rule of this kind of shard is: Locate {@link ShardGroup} by id interval first, 
 * then, locate target shard by the hash value of record id. If a group shard contains this hash value, it is hitted!
 * 
 * @author laurence.geng
 * @see SingleShard
 * @see GroupShard
 */
public class Shard {
	
	protected String id;
	
	/** 
	 * The resource is the channel or accessor to r/w database.
	 * It could be a jdbc connection, a spring jdbc template, a hibernate session, a spring hibernate template, and so on. 
	 */	
	protected Object resource;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getResource() {
		return resource;
	}

	public void setResource(Object resource) {
		this.resource = resource;
	}

}
