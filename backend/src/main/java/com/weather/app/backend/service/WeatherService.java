package com.weather.app.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.app.backend.ai.prompt.PromptIntentEnum;
import com.weather.app.backend.ai.prompt.WeatherPromptBuilder;
import com.weather.app.backend.config.OpenWeatherConfig;
import com.weather.app.backend.config.cache.AiInterpretationCacheConfig;
import com.weather.app.backend.config.cache.WeatherCacheConfig;
import com.weather.app.backend.dto.GeocodingResponse;
import com.weather.app.backend.dto.OneCallWeatherResponse;
import com.weather.app.backend.dto.WeatherResponse;
import com.weather.app.backend.dto.ai.AiWeatherContext;
import com.weather.app.backend.mapper.WeatherAiNormalizer;


@Service
public class WeatherService {
	
	private final RestTemplate restTemplate;
	private final OpenWeatherConfig config;
	private final OpenAiService aiService;
	private final GeocodingService geocodingService;
	private final ObjectMapper objectMapper;
	
	public WeatherService(RestTemplate restTemplate, OpenWeatherConfig config, GeocodingService geocodingService, OpenAiService aiService) {
		this.restTemplate = restTemplate;
		this.config = config;
		this.geocodingService = geocodingService;
		this.objectMapper = new ObjectMapper();
		this.aiService = aiService;
	}


	public WeatherResponse getWeatherByCoordinates(double lat, double lon) {
		String url = String.format(
				"%s/data/2.5/weather?lat=%f&lon=%f&units=metric&lang=pt_br&appid=%s",
	            config.getBaseUrl(), lat, lon, config.getApiKey());
		
		return restTemplate.getForObject(url, WeatherResponse.class);
	}
	
	public OneCallWeatherResponse getCompleteWeatherByCoordinates(double lat, double lon, String lang) {
		String formattedLang = lang.substring(0, 2);
		String cacheKey = "onecall:" + lat + ":" + lon + ":" + formattedLang;
		
		OneCallWeatherResponse oneCallResponse = WeatherCacheConfig.weatherCache.get(cacheKey, k -> {
			System.out.println("Cache miss: " + k);
			String url = String.format(
					"%s/data/3.0/onecall?lat=%f&lon=%f&units=metric&lang=%s&appid=%s",
					config.getBaseUrl(), lat, lon, formattedLang, config.getApiKey());
			return restTemplate.getForObject(url, OneCallWeatherResponse.class);
		});
		
		GeocodingResponse localization = getLocalization(lat, lon, formattedLang);
		if (localization.getLocalNames() != null) {
			oneCallResponse.setCity(localization.getLocalNames().getOrDefault(formattedLang, localization.getName()));			
		} else {
			oneCallResponse.setCity(localization.getName());
		}
		oneCallResponse.setCountry(localization.getCountry());
		oneCallResponse.setLanguage(formattedLang);

		oneCallResponse.setAiInterpretation(getAiResponseInterpretation(oneCallResponse));
		
		return oneCallResponse;
	}


	private String getAiResponseInterpretation(OneCallWeatherResponse response) {
		AiWeatherContext aiWeatherContext = WeatherAiNormalizer.normalize(response);
		WeatherPromptBuilder promptBuilder = new WeatherPromptBuilder(objectMapper);
		String prompt = promptBuilder.build(aiWeatherContext, PromptIntentEnum.GENERAL_SUMMARY);
		
		return AiInterpretationCacheConfig.aiInterpretationCache.get(aiWeatherContext, _ -> {
			return aiService.responder(prompt);
		});
	}
	
	private GeocodingResponse getLocalization(double lat, double lon, String lang) {
		return geocodingService.buscarCidadePorCoordenadas(lat, lon, lang);
	}
	
}
