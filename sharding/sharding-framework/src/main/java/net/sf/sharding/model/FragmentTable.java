package net.sf.sharding.model;

/**
 * We do not intend to involve FragmentTable, because If we involve it in, we have to filter all sql, 
 * and replace table name target fragment tabel name. if system use a kind of ORM framework, it would 
 * be harder to realize the replacement. we hope this demo is simple and easy.
 * <p>
 * If we have to use fragment table, we could add filter method for {@link Partition}, 
 * and let {@link ShardLocator} delegate filter operation to target {@link Partition}. 
 * For example:
 * String filteredSql = shardLocator.filter(partitionId,sql);
 * shardLocator.getShard(partitionId,masterTableId).getJdbcTemplate().execute(filteredSql);
 * <p>
 * The ideal substitute is "Partition" mechanism of some databases.
 * 
 * @author laurence.geng
 * @deprecated
 *
 */
public class FragmentTable {

	private String id;
	
	private Long startId;
	
	private Long endId;

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
	
	
}
