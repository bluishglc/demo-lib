package net.sf.sharding.model;

import java.util.List;


/**
 * The Shard of a ShardGroup. It has a hash value list which indicates what id should locate
 * this shard. If the hash value of a certain record id is in the has value list, it's in.
 * 
 * @author laurence.geng
 * @see Shard
 */
public class GroupShard extends Shard {

	private List<Long> hashValues;
	
	public boolean hasHashValue(Long hashValue){
		return hashValues.contains(hashValue);
	}

	public List<Long> getHashValues() {
		return hashValues;
	}

	public void setHashValues(List<Long> hashValues) {
		this.hashValues = hashValues;
	}
	
}
