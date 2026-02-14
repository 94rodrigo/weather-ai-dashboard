package com.weather.app.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OneCallWeatherResponse {

	private Double lat;
	private Double lon;
	private String city;
	private String country;
	private String timezone;
	private Integer timezone_offset;
	private String aiInterpretation;
	private String language;

	private Current current;
	private List<Hourly> hourly;
	private List<Daily> daily;
	private List<Alert> alerts;

	// ================= CURRENT =================
	public static class Current {
		private Long dt;
		private Long sunrise;
		private Long sunset;

		private Double temp;
		private Double feels_like;
		private Integer pressure;
		private Integer humidity;
		private Double uvi;

		private Integer clouds;
		private Integer visibility;

		private Double wind_speed;
		private Integer wind_deg;
		private Double wind_gust;

		private List<Weather> weather;

		// One Call retorna objeto { "1h": x }
		private Rain rain;
		private Snow snow;

		public Long getDt() {
			return dt;
		}

		public void setDt(Long dt) {
			this.dt = dt;
		}

		public Long getSunrise() {
			return sunrise;
		}

		public void setSunrise(Long sunrise) {
			this.sunrise = sunrise;
		}

		public Long getSunset() {
			return sunset;
		}

		public void setSunset(Long sunset) {
			this.sunset = sunset;
		}

		public Double getTemp() {
			return temp;
		}

		public void setTemp(Double temp) {
			this.temp = temp;
		}

		public Double getFeels_like() {
			return feels_like;
		}

		public void setFeels_like(Double feels_like) {
			this.feels_like = feels_like;
		}

		public Integer getPressure() {
			return pressure;
		}

		public void setPressure(Integer pressure) {
			this.pressure = pressure;
		}

		public Integer getHumidity() {
			return humidity;
		}

		public void setHumidity(Integer humidity) {
			this.humidity = humidity;
		}

		public Double getUvi() {
			return uvi;
		}

		public void setUvi(Double uvi) {
			this.uvi = uvi;
		}

		public Integer getClouds() {
			return clouds;
		}

		public void setClouds(Integer clouds) {
			this.clouds = clouds;
		}

		public Integer getVisibility() {
			return visibility;
		}

		public void setVisibility(Integer visibility) {
			this.visibility = visibility;
		}

		public Double getWind_speed() {
			return wind_speed;
		}

		public void setWind_speed(Double wind_speed) {
			this.wind_speed = wind_speed;
		}

		public Integer getWind_deg() {
			return wind_deg;
		}

		public void setWind_deg(Integer wind_deg) {
			this.wind_deg = wind_deg;
		}

		public Double getWind_gust() {
			return wind_gust;
		}

		public void setWind_gust(Double wind_gust) {
			this.wind_gust = wind_gust;
		}

		public List<Weather> getWeather() {
			return weather;
		}

		public void setWeather(List<Weather> weather) {
			this.weather = weather;
		}

		public Rain getRain() {
			return rain;
		}

		public void setRain(Rain rain) {
			this.rain = rain;
		}

		public Snow getSnow() {
			return snow;
		}

		public void setSnow(Snow snow) {
			this.snow = snow;
		}
	}

	// ================= HOURLY =================
	public static class Hourly {
		private Long dt;
		private Double temp;
		private Double feels_like;
		private Integer pressure;
		private Integer humidity;

		private Double pop;

		private Double wind_speed;
		private Integer wind_deg;

		private List<Weather> weather;

		// One Call retorna objeto { "1h": x }
		private Rain rain;
		private Snow snow;

		public Long getDt() {
			return dt;
		}

		public void setDt(Long dt) {
			this.dt = dt;
		}

		public Double getTemp() {
			return temp;
		}

		public void setTemp(Double temp) {
			this.temp = temp;
		}

		public Double getFeels_like() {
			return feels_like;
		}

		public void setFeels_like(Double feels_like) {
			this.feels_like = feels_like;
		}

		public Integer getPressure() {
			return pressure;
		}

		public void setPressure(Integer pressure) {
			this.pressure = pressure;
		}

		public Integer getHumidity() {
			return humidity;
		}

		public void setHumidity(Integer humidity) {
			this.humidity = humidity;
		}

		public Double getPop() {
			return pop;
		}

		public void setPop(Double pop) {
			this.pop = pop;
		}

		public Double getWind_speed() {
			return wind_speed;
		}

		public void setWind_speed(Double wind_speed) {
			this.wind_speed = wind_speed;
		}

		public Integer getWind_deg() {
			return wind_deg;
		}

		public void setWind_deg(Integer wind_deg) {
			this.wind_deg = wind_deg;
		}

		public List<Weather> getWeather() {
			return weather;
		}

		public void setWeather(List<Weather> weather) {
			this.weather = weather;
		}

		public Rain getRain() {
			return rain;
		}

		public void setRain(Rain rain) {
			this.rain = rain;
		}

		public Snow getSnow() {
			return snow;
		}

		public void setSnow(Snow snow) {
			this.snow = snow;
		}

	}

	// ================= DAILY =================
	public static class Daily {
		private Long dt;
		private Long sunrise;
		private Long sunset;

		private Temp temp;
		private Integer humidity;
		private Double wind_speed;
		private Integer wind_deg;

		private List<Weather> weather;
		private Double pop;

		// ⚠️ One Call retorna NÚMERO, não objeto
		private Double rain;
		private Double snow;

		public Long getDt() {
			return dt;
		}

		public void setDt(Long dt) {
			this.dt = dt;
		}

		public Long getSunrise() {
			return sunrise;
		}

		public void setSunrise(Long sunrise) {
			this.sunrise = sunrise;
		}

		public Long getSunset() {
			return sunset;
		}

		public void setSunset(Long sunset) {
			this.sunset = sunset;
		}

		public Temp getTemp() {
			return temp;
		}

		public void setTemp(Temp temp) {
			this.temp = temp;
		}

		public Integer getHumidity() {
			return humidity;
		}

		public void setHumidity(Integer humidity) {
			this.humidity = humidity;
		}

		public Double getWind_speed() {
			return wind_speed;
		}

		public void setWind_speed(Double wind_speed) {
			this.wind_speed = wind_speed;
		}

		public Integer getWind_deg() {
			return wind_deg;
		}

		public void setWind_deg(Integer wind_deg) {
			this.wind_deg = wind_deg;
		}

		public List<Weather> getWeather() {
			return weather;
		}

		public void setWeather(List<Weather> weather) {
			this.weather = weather;
		}

		public Double getPop() {
			return pop;
		}

		public void setPop(Double pop) {
			this.pop = pop;
		}

		public Double getRain() {
			return rain;
		}

		public void setRain(Double rain) {
			this.rain = rain;
		}

		public Double getSnow() {
			return snow;
		}

		public void setSnow(Double snow) {
			this.snow = snow;
		}

	}

	// ================= TEMP =================
	public static class Temp {
		private Double day;
		private Double min;
		private Double max;
		private Double night;
		private Double eve;
		private Double morn;

		public Double getDay() {
			return day;
		}

		public void setDay(Double day) {
			this.day = day;
		}

		public Double getMin() {
			return min;
		}

		public void setMin(Double min) {
			this.min = min;
		}

		public Double getMax() {
			return max;
		}

		public void setMax(Double max) {
			this.max = max;
		}

		public Double getNight() {
			return night;
		}

		public void setNight(Double night) {
			this.night = night;
		}

		public Double getEve() {
			return eve;
		}

		public void setEve(Double eve) {
			this.eve = eve;
		}

		public Double getMorn() {
			return morn;
		}

		public void setMorn(Double morn) {
			this.morn = morn;
		}

	}

	// ================= WEATHER =================
	public static class Weather {
		private Long id;
		private String main;
		private String description;
		private String icon;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMain() {
			return main;
		}

		public void setMain(String main) {
			this.main = main;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

	}

	// ================= RAIN / SNOW (current/hourly) =================
	public static class Rain {
		@JsonProperty("1h")
		private Double oneHour;
	}

	public static class Snow {
		@JsonProperty("1h")
		private Double oneHour;
	}

	// ================= ALERTS =================
	public static class Alert {
		private String sender_name;
		private String event;
		private Long start;
		private Long end;
		private String description;

		public String getSender_name() {
			return sender_name;
		}

		public void setSender_name(String sender_name) {
			this.sender_name = sender_name;
		}

		public String getEvent() {
			return event;
		}

		public void setEvent(String event) {
			this.event = event;
		}

		public Long getStart() {
			return start;
		}

		public void setStart(Long start) {
			this.start = start;
		}

		public Long getEnd() {
			return end;
		}

		public void setEnd(Long end) {
			this.end = end;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Integer getTimezone_offset() {
		return timezone_offset;
	}

	public void setTimezone_offset(Integer timezone_offset) {
		this.timezone_offset = timezone_offset;
	}

	public String getAiInterpretation() {
		return aiInterpretation;
	}

	public void setAiInterpretation(String aiInterpretation) {
		this.aiInterpretation = aiInterpretation;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Current getCurrent() {
		return current;
	}

	public void setCurrent(Current current) {
		this.current = current;
	}

	public List<Hourly> getHourly() {
		return hourly;
	}

	public void setHourly(List<Hourly> hourly) {
		this.hourly = hourly;
	}

	public List<Daily> getDaily() {
		return daily;
	}

	public void setDaily(List<Daily> daily) {
		this.daily = daily;
	}

	public List<Alert> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}

}
