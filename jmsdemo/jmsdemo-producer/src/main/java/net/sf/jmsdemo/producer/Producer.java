package net.sf.jmsdemo.producer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class Producer {
	
	private JmsTemplate jmsTemplate;
	
	private Queue myQueue;
	
	public void produceMessage(){		
		jmsTemplate.send(myQueue, new MessageCreator() {			
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("Hello!");
			}
		});
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setMyQueue(Queue myQueue) {
		this.myQueue = myQueue;
	}
	
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext-jms.xml");
		Producer producer = (Producer) applicationContext.getBean("producer");
		for (int i = 0; i < 10; i++) {
			producer.produceMessage();
			System.out.println("Message "+i+" has sent out.");
			Thread.sleep(500L);
		}
	}

}
