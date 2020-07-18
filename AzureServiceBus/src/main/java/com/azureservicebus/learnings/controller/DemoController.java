package com.azureservicebus.learnings.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azureservicebus.learnings.queue.QueueSender;

@RestController
public class DemoController {

	@Autowired
	private QueueSender mSender;

	public static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@PostMapping("/sendMessage")
	public String sendMessage(@RequestParam String message) {
		logger.debug("Start : sendMessage() ");
		mSender.postMessage(message);
		logger.debug("End : sendMessage() ");
		return message;
	}
}
