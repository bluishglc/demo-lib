package net.sf.sharding.model;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * A MultiShardPartition is a partition after horizontal sharding. It contains one or more {@link ShardGroup}.
 * 
 * @author laurence.geng
 * @see Partition
 */
public class MultiShardPartition extends Partition {

	private static final Logger logger = Logger.getLogger(MultiShardPartition.class);
	
	private List<ShardGroup> shardGroups;
	
	public Shard getShard(Long masterTableId){
		ShardGroup shardGroup = getShardGroup(masterTableId);
		Shard shard = shardGroup.getShard(masterTableId);
		if(logger.isDebugEnabled()){
			logger.debug("Shard Route(id="+masterTableId+"): ["+id+"] => ["+ shardGroup.getId()+"] => ["+shard.getId()+"]");
		}
		return shard;
	}
	
	/**
	 * Gets the shard group by given master table id and each group id interval.
	 *
	 * @param masterTableId the master table id
	 * @return the shard group
	 */
	private ShardGroup getShardGroup(Long masterTableId){
		ShardGroup targetShardGroup = null; 
		for (ShardGroup shardGroup : shardGroups) {
			if(shardGroup.getStartId() <= masterTableId && masterTableId <= shardGroup.getEndId()){
				targetShardGroup = shardGroup;
				break;
			}
		}
		if(targetShardGroup==null){
			throw new RuntimeException("The given mater table id: "+masterTableId+" is out of planning scale, its invalid!");
		}
		return targetShardGroup;
	}

	public void setShardGroups(List<ShardGroup> shardGroups) {
		this.shardGroups = shardGroups;
	}

}
