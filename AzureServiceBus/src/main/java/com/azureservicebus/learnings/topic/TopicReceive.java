package com.azureservicebus.learnings.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import com.azureservicebus.learnings.queue.QueueSender;

public class TopicReceive {
	private static final String TOPIC_NAME = "<ServiceBusTopicName>";

	private static final String SUBSCRIPTION_NAME = "<ServiceBusSubscriptionName>";

	public static final Logger logger = LoggerFactory.getLogger(TopicReceive.class);

	@JmsListener(destination = TOPIC_NAME, containerFactory = "topicJmsListenerContainerFactory", subscription = SUBSCRIPTION_NAME)
	public void receiveMessage(String user) {
		logger.info("Received message: {}", user);
	}
}
