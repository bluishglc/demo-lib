package net.sf.idgenerator;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class IdGeneratorTest extends TestCase {
	
	@Autowired
	private MyIsamIdGenerator myIsamIdGenerator;
	
	@Autowired
	private InnoDbIdGenerator innoDbIdGenerator;
	
	@Test
	public void test(){
		System.out.println("\n-----------------------------  MyIsam: Single Thread, 1000 Ids ----------------------------------------");
		testNext(myIsamIdGenerator);
		
		System.out.println("\n-----------------------------  MyIsam: Multiple Thread, 10 Thread * 100 Ids ----------------------------------------");
		testNextWithMultiThread(myIsamIdGenerator);
		
		System.out.println("\n-----------------------------  InnoDB: Single Thread, 1000 Ids ----------------------------------------");
		testNext(innoDbIdGenerator);
		
		System.out.println("\n-----------------------------  InnoDB: Multiple Thread, 10 Thread * 100 Ids ----------------------------------------");
		testNextWithMultiThread(innoDbIdGenerator);
		
	}

	private void testNext(IdGenerator idGenerator) {
		Long time1 = System.currentTimeMillis();
		List<Long> ids = new ArrayList<Long>();
		for(int i=0;i<1000;i++){
			Long id = idGenerator.next();
			ids.add(id);
		}
		Long time2 = System.currentTimeMillis();
		System.out.println("Total Time: "+(time2-time1));
		System.out.println(ids);
	}
	
	private void testNextWithMultiThread(IdGenerator idGenerator){
		try {
			int cnt = 10;
			IdRequest[] requests = new IdRequest[cnt];
			Long time1 = System.currentTimeMillis();
			for(int i=0;i<cnt;i++){
				requests[i]= new IdRequest(idGenerator);
				requests[i].start();
			}
			for(int i=0;i<cnt;i++){
				requests[i].join();
			}
			Long time2 = System.currentTimeMillis();
			System.out.println("Total Time: "+(time2-time1));
			for(int i=0;i<cnt;i++){
				System.out.println(requests[i].getIds());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static class IdRequest extends Thread {
		
		private IdGenerator idGenerator;
		
		private List<Long> ids;

		public IdRequest(IdGenerator idGenerator) {
			super();
			this.idGenerator = idGenerator;
		}

		@Override
		public void run() {
			ids = new ArrayList<Long>();
			for(int i=0;i<100;i++){
				Long id = idGenerator.next();
				ids.add(id);
			}
		}

		public List<Long> getIds() {
			return ids;
		}
		
	}

}
