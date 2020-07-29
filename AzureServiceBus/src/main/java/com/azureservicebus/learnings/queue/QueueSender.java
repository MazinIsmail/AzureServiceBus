package com.azureservicebus.learnings.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/*
 * JmsTemplate simplifies receiving and sending of messages through JMS and gets rid of the boilerplate code 
 * It is the helper class that simplifies synchronous JMS access code and central class for Spring JMS integration
 * In JmsTemplate by default the transaction and acknowledgement mode are disable, 
 * if you want transaction and acknowledgement we need to configure on property file.
 * While doing transaction we need to take care settings. 
 *
 * convertAndSend method take the destination name and message. 
 * it will dynamically take the destination resource name and bind to JMS destination resolver 
 */

@Component
public class QueueSender {

	@Autowired
	private Environment env;

	@Autowired
	private JmsTemplate jmsTemplate;

	public static final Logger logger = LoggerFactory.getLogger(QueueSender.class);

	public void postMessage(String megString) {
		logger.debug("Start : postMessage() ");
		String queueName = env.getProperty("azure.servicebus.queuename");
		logger.info("Message is {}", megString);
		logger.info("Queue is {}", queueName);
		try {
			jmsTemplate.convertAndSend(queueName, megString);
		} catch (JmsException e) {
			ConnectionFactory connectionFactory = null;
			Connection connection = null;
			Session session = null;
			try {
				connectionFactory = jmsTemplate.getConnectionFactory();
				connection = connectionFactory.createConnection();
				connection.start();
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				Destination destination = session.createQueue(queueName);
				jmsTemplate.convertAndSend(queueName, megString);
			} catch (JmsException | JMSException e1) {
				logger.error("Error :: postMessage :: {}", e1);
			} finally {
				try {
					session.close();
					connection.close();
				} catch (JMSException e1) {
					logger.error("Error :: postMessage Closing Resource Failed :: {}", e1);
				}
			}
		}
		logger.debug("End : postMessage() ");
	}

}
