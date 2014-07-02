package net.sf.sharding.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Maybe, we don't need this locator, for most DAOs, they only depends on a certain partition,
 * we can inject this partition directly.
 * @author laurence.geng
 * @deprecated
 */
public class ShardLocator {
	
	private Map<String,SingleShardPartition> singleShardPartitions;
	
	private Map<String,MultiShardPartition> multiShardPartitions;
	
	public ShardLocator(Map<String, Partition> partitions) {
		singleShardPartitions = new HashMap<String, SingleShardPartition>();
		multiShardPartitions = new HashMap<String, MultiShardPartition>();
		Set<Entry<String, Partition>> partitionSet = partitions.entrySet();
		for (Entry<String, Partition> entry : partitionSet) {
			String partitionId = entry.getKey();
			Partition partition = entry.getValue();
			if (partition instanceof SingleShardPartition) {
				singleShardPartitions.put(partitionId, (SingleShardPartition) partition);
			}else{
				multiShardPartitions.put(partitionId, (MultiShardPartition) partition);
			}
		}
	}
	
	public Shard getShard(String partitionId){
		SingleShardPartition partition =  getSingleShardPartition(partitionId);
		Shard targetShard = partition.getShard();
		return  targetShard;		
	}
	
	/**
	 * Gets target shard by given partition & master table id. The shard locates in a multi-shard
	 * partition, so, we have to provide master table it to locate the target shard.
	 *
	 * @param partitionId the partition id
	 * @param masterTableId the master table is the table which id is suppose to use when routing. For a shard, there's only one master table. 
	 * @return The target shard 
	 */
	public Shard getShard(String partitionId, Long masterTableId){
		MultiShardPartition partition = getMultiShardPartition(partitionId);
		Shard targetShard = partition.getShard(masterTableId);
		return targetShard;
	}
	
	private SingleShardPartition getSingleShardPartition(String partitionId){
		SingleShardPartition partition = singleShardPartitions.get(partitionId);
		if (partition ==  null) {
			throw new RuntimeException("No such partition or this partition is not single-shard partition!");
		} 
		return partition;
	}

	private MultiShardPartition getMultiShardPartition(String partitionId){
		MultiShardPartition partition = multiShardPartitions.get(partitionId);
		if(partition == null){
			throw new RuntimeException("No such partition or this partition is not multi-shard partition!");
		}
		return partition;
	}

}
