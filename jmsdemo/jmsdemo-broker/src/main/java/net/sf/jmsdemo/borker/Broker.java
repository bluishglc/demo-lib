package net.sf.jmsdemo.borker;

import org.apache.activemq.broker.BrokerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Broker {
	
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext-jms.xml");
		BrokerService myBroker = (BrokerService) applicationContext.getBean("myBroker");
		// You can also set start="true" in applicationContext-jms.xml, let broker start when application context inited.
		myBroker.start(); 
		System.out.println("Press any key to exit...");
		System.in.read();
		myBroker.stop();
	}

}
