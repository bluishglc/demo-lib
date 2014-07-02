package com.ibatis.jpetstore.persistence.sharding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShardLocator {
	
	private Map<String,Partition> partitions = new HashMap<String,Partition>();
	
	public Shard getShard(String partitionId){
		Partition partition = partitions.get(partitionId);
		if (partition.size() == 1) {
			return partition.getShards().get(0);
		} else {
			throw new RuntimeException("The partition is not single-shard partition!");
		}
	}
	
	public Shard getShard(String partitionId, Long entityId){
		Partition partition = partitions.get(partitionId);
		int mod = partition.size();
		if ( mod > 1) {
			return partition.getShards().get((int) (entityId % mod));
		} else {
			throw new RuntimeException("The partition is not multi-shard partition!");
		}
	}

	public List<Shard> getShards(String partitionId){
		Partition partition = partitions.get(partitionId);
		return partition.getShards();
	}

	public void setPartitions(Map<String, Partition> partitions) {
		this.partitions = partitions;
	}

}
