package com.sandpit.spring.jta.simple.service;

import org.springframework.util.ErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JmsErrorHandler implements ErrorHandler {
	
	@Override
	public void handleError(Throwable t) {
		log.error("Fatal JMS Error", t);
	}

}
