package com.weather.app.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weather.app.backend.config.OpenWeatherConfig;
import com.weather.app.backend.config.cache.GeocodingCacheConfig;
import com.weather.app.backend.dto.GeocodingResponse;

@Service
public class GeocodingService {
	
	private final RestTemplate restTemplate;
	private final OpenWeatherConfig config;
	
	public GeocodingService(RestTemplate restTemplate, OpenWeatherConfig config) {
		this.restTemplate = restTemplate;
		this.config = config;
	}
	
	public List<GeocodingResponse> searchCity(String query, String lang) {
		String cacheKey = "geocoding:" + query + ":" + lang;
		
		GeocodingResponse[] responses = GeocodingCacheConfig.geocodingCache.get(cacheKey, k -> {
			System.out.println("Cache miss: " + k);
			String url = String.format(
					"%s/geo/1.0/direct?q=%s&limit=20&lang=%s&appid=%s",
					config.getBaseUrl(), query, lang, config.getApiKey());
			
			return restTemplate.getForObject(url, GeocodingResponse[].class);
		});
		
		return List.of(responses);
	}
	
	public GeocodingResponse buscarCidadePorCoordenadas(double lat, double lon, String lang) {
		String cacheKey = "geocoding:" + lat + ":" + lon;
		
		GeocodingResponse[] responses =  GeocodingCacheConfig.geocodingCache.get(cacheKey, k -> {
			System.out.println("Cache miss: " + k);
			String url = String.format(
					"%s/geo/1.0/reverse?lat=%f&lon=%f&lang=%s&appid=%s",
					config.getBaseUrl(), lat, lon, lang, config.getApiKey());
			
			return restTemplate.getForObject(url, GeocodingResponse[].class);
		});
		
		return List.of(responses).getFirst();
	}

}
