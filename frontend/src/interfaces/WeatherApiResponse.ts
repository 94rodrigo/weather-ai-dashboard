export interface WeatherApiResponse {
  lat: number;
  lon: number;
  city: string;
  country: string;
  timezone: string;
  timezone_offset: number;
  aiInterpretation: string;
  current: CurrentWeather;
  hourly: HourlyWeather[];
  daily: DailyWeather[];
  alerts: Alert[] | null;
}

interface Alert {
  event: string;
  start: number;
  end: number;
  description: string;
}

interface Weather {
  id: number;
  main: string;
  description: string;
  icon: string;
}

interface Precipitation {
  "1h": number;
}

interface CurrentWeather {
  dt: number;
  sunrise: number;
  sunset: number;
  temp: number;
  feels_like: number;
  pressure: number;
  humidity: number;
  uvi: number;
  clouds: number;
  visibility: number;
  wind_speed: number;
  wind_deg: number;
  wind_gust?: number;
  weather: Weather[];
  rain: Precipitation | null;
  snow: Precipitation | null;
}

interface HourlyWeather {
  dt: number;
  temp: number;
  feels_like: number;
  pressure: number;
  humidity: number;
  pop: number; // probabilidade de chuva
  wind_speed: number;
  wind_deg: number;
  weather: Weather[];
  rain: Precipitation | null;
  snow: Precipitation | null;
}

interface DailyTemp {
  day: number;
  min: number;
  max: number;
  night: number;
  eve: number;
  morn: number;
}

interface DailyWeather {
  dt: number;
  sunrise: number;
  sunset: number;
  temp: DailyTemp;
  humidity: number;
  wind_speed: number;
  wind_deg: number;
  weather: Weather[];
  pop: number;
  rain: number | null;
  snow: number | null;
}
