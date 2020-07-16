package com.azureservicebus.learnings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

/*
 * Azure Service Bus is provided by Microsoft and it is enterprise service. 
 * It is one of the message broker, it has fully managed and integrated with all protocols of Advanced Message Queuing Protocol (AMQP)
 * The major features are message orientation, queuing, routing including point-to-point and publish-and-subscribe with reliability and security
 * Queues are used for point to point with asynchronous communication 
 * Topics are used for publish-and-subscribe with asynchronous communication
 *
 * Enable JMS annotation will provide all required configuration of JMS service
 * It will create JmsListenerContainerFactory container and all other Message handlers components 
 */

@SpringBootApplication
@ComponentScan("com.azureservicebus.learnings*")
@EnableJms
public class AzureServiceBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzureServiceBusApplication.class, args);
	}

}
