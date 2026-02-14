package com.weather.app.backend.config.cache;

import java.time.Duration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.weather.app.backend.dto.OneCallWeatherResponse;

public class WeatherCacheConfig {

	public static Cache<String, OneCallWeatherResponse> weatherCache = 
			Caffeine.newBuilder()
					.expireAfterWrite(Duration.ofMinutes(20))
					.maximumSize(10_000)
					.build();
}
