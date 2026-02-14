package com.weather.app.backend.dto.ai;

import java.util.List;

public class AiWeatherContext {

    private String location;
    private Current current;
    private List<HourForecast> nextHours;
    private List<DayForecast> nextDays;
    private List<String> alerts;
    private String language;

    public static class Current {
        private Double temp;
        private Double feelsLike;
        private Integer humidity;
        private Double uvi;
        private Double windSpeed;
        private String weather;
		public Double getTemp() {
			return temp;
		}
		public void setTemp(Double temp) {
			this.temp = temp;
		}
		public Double getFeelsLike() {
			return feelsLike;
		}
		public void setFeelsLike(Double feelsLike) {
			this.feelsLike = feelsLike;
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
		public Double getWindSpeed() {
			return windSpeed;
		}
		public void setWindSpeed(Double windSpeed) {
			this.windSpeed = windSpeed;
		}
		public String getWeather() {
			return weather;
		}
		public void setWeather(String weather) {
			this.weather = weather;
		}
    }

    public static class HourForecast {
        private String hour;       // "14:00"
        private Double temp;
        private Double rainProb;   // 0.0 - 1.0
		public String getHour() {
			return hour;
		}
		public void setHour(String hour) {
			this.hour = hour;
		}
		public Double getTemp() {
			return temp;
		}
		public void setTemp(Double temp) {
			this.temp = temp;
		}
		public Double getRainProb() {
			return rainProb;
		}
		public void setRainProb(Double rainProb) {
			this.rainProb = rainProb;
		}
    }

    public static class DayForecast {
        private String day;        // "Hoje", "Amanhã"
        private Double min;
        private Double max;
        private Double rainProb;
		public String getDay() {
			return day;
		}
		public void setDay(String day) {
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
		public Double getRainProb() {
			return rainProb;
		}
		public void setRainProb(Double rainProb) {
			this.rainProb = rainProb;
		}
    }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Current getCurrent() {
		return current;
	}

	public void setCurrent(Current current) {
		this.current = current;
	}

	public List<HourForecast> getNextHours() {
		return nextHours;
	}

	public void setNextHours(List<HourForecast> nextHours) {
		this.nextHours = nextHours;
	}

	public List<DayForecast> getNextDays() {
		return nextDays;
	}

	public void setNextDays(List<DayForecast> nextDays) {
		this.nextDays = nextDays;
	}

	public List<String> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<String> alerts) {
		this.alerts = alerts;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
    
}