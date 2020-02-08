package com.projectjb.couponsystemjb.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.projectjb.couponsystemjb.rest.Session;

@Configuration
public class RestConfig {
	@Bean
	public Map<String, Session> tokensMap() {
		return new HashMap<String, Session>();
	}

}
