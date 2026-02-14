package com.weather.app.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenWeatherConfig {

	@Value("${openweather.api.key}")
	private String apiKey;
	
	@Value("${openweather.base.url}")
	private String baseUrl;
	
	public String getApiKey() {
		return apiKey;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
	
}
