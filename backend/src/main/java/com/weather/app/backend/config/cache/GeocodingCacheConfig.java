package com.weather.app.backend.config.cache;

import java.time.Duration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.weather.app.backend.dto.GeocodingResponse;

public class GeocodingCacheConfig {
	
	public static Cache<String, GeocodingResponse[]> geocodingCache = 
			Caffeine.newBuilder()
					.expireAfterWrite(Duration.ofDays(1L))
					.maximumSize(10_000)
					.build();
}
