package com.ibatis.jpetstore.persistence.sharding;

import java.util.ArrayList;
import java.util.List;

public class Partition {
	
	public static final String ACCOUNT = "accountPartition";
	public static final String ORDER = "orderPartition";
	public static final String PRODUCT = "productPartition";
	public static final String SEQUENCE = "sequencePartition";
	
	private String id;

	private List<Shard> shards = new ArrayList<Shard>();
	
	public int size(){
		return shards.size();
	}	

	public void addShard(Shard shard){
		shards.add(shard);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
		
	public void setShards(List<Shard> shards) {
		this.shards = shards;
	}

	public List<Shard> getShards() {
		return shards;
	}
	
}
