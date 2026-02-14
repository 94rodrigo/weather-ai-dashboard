package com.weather.app.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.app.backend.dto.OneCallWeatherResponse;
import com.weather.app.backend.dto.WeatherResponse;
import com.weather.app.backend.service.WeatherService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/weather")
public class WeatherController {

	private final WeatherService weatherService;
	
	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
	@GetMapping("/simple")
	public WeatherResponse getWeather(@RequestParam double lat, @RequestParam double lon) {
		return weatherService.getWeatherByCoordinates(lat, lon);
	}
	
	@GetMapping("/complete")
	public OneCallWeatherResponse getWeatherComplete(@RequestParam double lat,
													@RequestParam double lon,
													@RequestHeader(value = "Accept-Language", defaultValue = "en") String lang) {
		return weatherService.getCompleteWeatherByCoordinates(lat, lon, lang);
	}
}
