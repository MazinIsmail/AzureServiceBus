package com.azureservicebus.learnings.listerner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/*
 * JmsListener will help to create the JmsListenerContainerFactory by using destination resource
 * this will provide session, message, headers and all other information from destination resource
 * Here the destination we are using azure bus service queue.  
 */

@Component
public class MessageListener {
	public static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

	@JmsListener(destination = "${azure.servicebus.queuename}", containerFactory = "jmsListenerContainerFactory")
	public void messageReceiver(String message) {
		logger.debug("Start : messageReceiver() ");
		logger.info("The received messag is {}", message);
		logger.debug("End : messageReceiver() ");
	}
}
