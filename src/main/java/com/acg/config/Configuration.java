package com.acg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class Configuration {

	@Value("${server.port}")
	public int serverPort;


	private Configuration() {
		// TODO Auto-generated constructor stub
	}

}
