package com.weather.app.backend.mapper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.weather.app.backend.dto.OneCallWeatherResponse;
import com.weather.app.backend.dto.ai.AiWeatherContext;

public class WeatherAiNormalizer {

	private static final int HOURS_LIMIT = 3;
	private static final int DAYS_LIMIT = 2;

	private static final DateTimeFormatter HOUR_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

	private WeatherAiNormalizer() {
		// utility class
	}

	public static AiWeatherContext normalize(OneCallWeatherResponse source) {
		AiWeatherContext context = new AiWeatherContext();
		context.setLocation(source.getCity() + ", " + source.getCountry());

		context.setCurrent(mapCurrent(source));
		context.setNextHours(mapNextHours(source));
		context.setNextDays(mapNextDays(source));
		context.setAlerts(mapAlerts(source));
		context.setLanguage(source.getLanguage());

		return context;
	}

	// ================= CURRENT =================

	private static AiWeatherContext.Current mapCurrent(OneCallWeatherResponse source) {
		var current = source.getCurrent();
		if (current == null)
			return null;

		AiWeatherContext.Current c = new AiWeatherContext.Current();
		c.setTemp(current.getTemp());
		c.setFeelsLike(current.getFeels_like());
		c.setHumidity(current.getHumidity());
		c.setUvi(current.getUvi());
		c.setWindSpeed(current.getWind_speed());

		if (current.getWeather() != null && !current.getWeather().isEmpty()) {
			c.setWeather(current.getWeather().get(0).getDescription());
		}

		return c;
	}

	// ================= HOURLY =================

	private static List<AiWeatherContext.HourForecast> mapNextHours(OneCallWeatherResponse source) {
		if (source.getHourly() == null)
			return List.of();

		return source.getHourly().stream().limit(HOURS_LIMIT).map(hour -> {
			AiWeatherContext.HourForecast h = new AiWeatherContext.HourForecast();

			h.setHour(formatHour(hour.getDt(), source.getTimezone()));

			h.setTemp(hour.getTemp());
			h.setRainProb(hour.getPop());

			return h;
		}).collect(Collectors.toList());
	}

	// ================= DAILY =================

	private static List<AiWeatherContext.DayForecast> mapNextDays(OneCallWeatherResponse source) {
		if (source.getDaily() == null)
			return List.of();

		return IntStream.range(0, Math.min(DAYS_LIMIT, source.getDaily().size()))
				.mapToObj(index -> {
					var day = source.getDaily().get(index);
		
					AiWeatherContext.DayForecast d = new AiWeatherContext.DayForecast();
		
					d.setDay(index == 0 ? "Hoje" : "Amanhã");
					d.setMin(day.getTemp().getMin());
					d.setMax(day.getTemp().getMax());
					d.setRainProb(day.getPop());
		
					return d;
				})
				.collect(Collectors.toList());
	}

	// ================= ALERTS =================

	private static List<String> mapAlerts(OneCallWeatherResponse source) {
		if (source.getAlerts() == null)
			return List.of();

		return source.getAlerts().stream().map(alert -> alert.getEvent()).collect(Collectors.toList());
	}

	// ================= UTILS =================

	private static String formatHour(Long epoch, String timezone) {
		return Instant.ofEpochSecond(epoch).atZone(ZoneId.of(timezone)).format(HOUR_FORMAT);
	}
}