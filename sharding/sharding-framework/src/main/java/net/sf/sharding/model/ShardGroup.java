package net.sf.sharding.model;

import java.util.List;

/**
 * Each {@link MultiShardPartition} has one or more ShardGroups. Each ShardGroup has an ID interval which indicate
 * what records stored in this ShardGroup. In ShardGroup, there are one or more {@link GroupShard}. A record which id
 * is in the interval of a ShardGroup will store in a certain GroupShard by the hash value of its id.
 * 
 * @author laurence.geng
 * @see Partition
 */
public class ShardGroup {

	private String id;
	
	// Property: writable is required, usually, the shard group with highest id interval is writable! 
	//private Boolean writable; 
	
	private Long startId;
	
	private Long endId;
	
	private Long mod;
	
	private List<GroupShard> shards;
	
	public Shard getShard(Long masterTableId){
		Shard targetShard = null;
		Long hashValue = masterTableId%mod;
		for (GroupShard shard : shards) {
			if (shard.hasHashValue(hashValue)) {
				targetShard = shard;
				break;
			}
		}
		if(targetShard == null){
			throw new RuntimeException("The hash value settings of shards in shard group: "+id
					+" has error! the recorde id: "+masterTableId+" can't map to a certain shard!");
		}
		return targetShard;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getStartId() {
		return startId;
	}

	public void setStartId(Long startId) {
		this.startId = startId;
	}

	public Long getEndId() {
		return endId;
	}

	public void setEndId(Long endId) {
		this.endId = endId;
	}

	public List<GroupShard> getShards() {
		return shards;
	}

	public void setShards(List<GroupShard> shards) {
		this.shards = shards;
	}

	public Long getMod() {
		return mod;
	}

	public void setMod(Long mod) {
		this.mod = mod;
	}

}
