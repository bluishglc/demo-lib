package net.sf.memcacheddemo;

import java.util.Random;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedClientDemo {

	public static void main(String[] args) {  

        String[] servers = { "localhost:11211","localhost:11212" };  
        SockIOPool pool = SockIOPool.getInstance();  
        pool.setServers(servers);  
        pool.setFailover(true);  
        pool.setInitConn(10);  
        pool.setMinConn(5);  
        pool.setMaxConn(250);  
        pool.setMaintSleep(30);  
        pool.setNagle(false);  
        pool.setSocketTO(3000);  
        pool.setAliveCheck(true);  
        pool.initialize();  

        MemCachedClient memCachedClient = new MemCachedClient(); 

        fill(memCachedClient);
        
        hitCache(memCachedClient);
	}
	
	public static void fill(MemCachedClient memCachedClient){
		for (int i = 0; i < 10; i++) {
			boolean success = memCachedClient.set("" + i, "Hello!");
			System.out.println(String.format("set( %d ): %s", i, success));
		}
		
	}
	
	public static void hitCache(MemCachedClient memCachedClient){
		Random r = new Random();

		for (int i = 0; i < 10; i++) {
			int key = r.nextInt(20);
			String result = (String) memCachedClient.get("" + key);    
            System.out.println(String.format("get( %d ): %s", key, result)); 
		} 
	}
}
