package com.ibatis.jpetstore.persistence.sharding;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-resources.xml"})
public class ShardLocatorTest {
	
	@Autowired
	private ShardLocator shardLocator;

	//@Test
	public void testGetShardString() {

	}

	@Test
	public void testGetShardStringLong() {
		try {
			Shard s1 = shardLocator.getShard("accountPartition", 1L);
			assertNotNull(s1.getSqlMapClientTemplate());
			Shard s2 = shardLocator.getShard("accountPartition", 2L);
			assertNotNull(s2.getSqlMapClientTemplate());
			Shard s3 = shardLocator.getShard("accountPartition", 3L);
			assertNotNull(s3.getSqlMapClientTemplate());
			Shard s4 = shardLocator.getShard("accountPartition", 4L);
			assertNotNull(s4.getSqlMapClientTemplate());
			
			assertNotSame(s1, s2);
			assertNotSame(s3, s4);
			assertSame(s1, s3);
			assertSame(s2, s4);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@Test
	public void testGetShards() {
		fail("Not yet implemented");
	}

}
