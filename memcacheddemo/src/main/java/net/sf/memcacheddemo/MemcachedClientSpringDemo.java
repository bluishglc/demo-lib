package net.sf.memcacheddemo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.danga.MemCached.MemCachedClient;

@Component
public class MemcachedClientSpringDemo {
	
	private MemCachedClient memCachedClient;

	public static void main(String[] args) {  
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		
		MemcachedClientSpringDemo demo = (MemcachedClientSpringDemo) ac.getBean("memcachedClientSpringDemo");
		
//		demo.fill();
        
		demo.hitCache();
	}
	
	public void fill(){
		for (int i = 0; i < 100; i++) {
			boolean success = memCachedClient.set("" + i, "Hello!");
			System.out.println(String.format("set( %d ): %s", i, success));
		}
		
	}
	
	public void hitCache(){
		Random r = new Random();

		for (int i = 0; i < 100; i++) {
			int key = r.nextInt(200);
			String result = (String) memCachedClient.get("" + key);    
            System.out.println(String.format("get( %d ): %s", key, result)); 
		} 
	}


	@Autowired
	public void setMemCachedClient(MemCachedClient memCachedClient) {
		this.memCachedClient = memCachedClient;
	}
	
	
}
