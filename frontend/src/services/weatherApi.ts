import i18n from "../config/i18n";
import type { WeatherApiResponse } from "../interfaces/WeatherApiResponse";

export async function searchCities(query: string) {
    const response = await fetch(`http://localhost:8080/api/geo/search?query=${query}`, {
        headers: {
            'Accept-Language': i18n.language
        }
    });
    return response.json();
}

export async function getWeather(lat: number, lon: number) {
    const response = await fetch(`http://localhost:8080/api/weather?lat=${lat}&lon=${lon}`);
    return response.json();
}

export async function getCompleteWeather(lat: number, lon: number): Promise<WeatherApiResponse> {
    const response = await fetch(`http://localhost:8080/api/weather/complete?lat=${lat}&lon=${lon}`, {
        headers: {
            'Accept-Language': i18n.language
        }
    });
    return response.json();
}