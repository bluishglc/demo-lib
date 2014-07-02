package net.sf.cxfdemo.client;

import net.sf.cxfdemo.server.Bar;
import net.sf.cxfdemo.server.DemoService;
import net.sf.cxfdemo.server.Foo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoClient {
	
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context 
        = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		DemoService demoService = (DemoService) context.getBean("demoService");
		Bar bar = demoService.execute(new Foo(new Long(1L),"ABC"));
		System.out.println(bar);
	}

}
