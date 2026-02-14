package com.weather.app.backend.config.cache;

import java.time.Duration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.weather.app.backend.dto.ai.AiWeatherContext;

public class AiInterpretationCacheConfig {

	public static Cache<AiWeatherContext, String> aiInterpretationCache = 
			Caffeine.newBuilder()
					.expireAfterWrite(Duration.ofMinutes(20))
					.maximumSize(10_000)
					.build();
}
