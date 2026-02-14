import { useRef, useState, useEffect } from "react";
import { useTranslation } from 'react-i18next';
import { getCompleteWeather, searchCities } from "../services/weatherApi";
import type { WeatherApiResponse } from "../interfaces/WeatherApiResponse";
import { WeatherDisplay } from "./WeatherDisplay";
import { useToast } from "./ToastContext";

type Props = {
    id: string;
    query: string;
    weather?: WeatherApiResponse;
    onQueryChange: (id: string, q: string) => void;
    onWeatherChange: (id: string, w: any) => void;
    onClose?: (id: string) => void;
    columnsQuantity: number;
    width: number;
};

export const WeatherColumn = ({ id, query, weather, onQueryChange, onWeatherChange, onClose, columnsQuantity, width }: Props) => {
    const { t, i18n } = useTranslation();
    const [suggestions, setSuggestions] = useState<any[]>([]);
    const [loading, setLoading] = useState(false);
    const timeoutRef = useRef<ReturnType<typeof setTimeout> | null>(null);
    const containerRef = useRef<HTMLDivElement>(null);
    const { showToast } = useToast();

    function getCityDisplayName(city: any) {
        const lang = i18n?.language?.split('-')[0] ?? 'en';
        return city.local_names?.[lang] ?? city.local_names?.en ?? city.name;
    }

    useEffect(() => {
        function handleClickOutside(event: MouseEvent) {
            if (containerRef.current && !containerRef.current.contains(event.target as Node)) {
                setSuggestions([]);
            }
        }

        document.addEventListener('mousedown', handleClickOutside);
        return () => document.removeEventListener('mousedown', handleClickOutside);
    }, []);

    async function handleSearch(cityQuery: string) {
        onQueryChange(id, cityQuery);
        
        if (timeoutRef.current) {
            clearTimeout(timeoutRef.current);
        }
        
        if (!cityQuery || cityQuery.length < 3) {
            return setSuggestions([]);
        }

        timeoutRef.current = setTimeout(async () => {
            setLoading(true);
            const res = await searchCities(cityQuery);
            setSuggestions(res);
            setLoading(false);
        }, 500);
    }

    async function handleCitySelect(city: any) {
        setSuggestions([]);
        setLoading(true);

        const weather: WeatherApiResponse = await getCompleteWeather(city.lat, city.lon);
        onWeatherChange(id, weather);

        setLoading(false);
    }

    async function handleRefresh() {
        if (!weather) return;
        setLoading(true);

        const refreshedWeather: WeatherApiResponse = await getCompleteWeather(weather.lat, weather.lon);
        onWeatherChange(id, refreshedWeather);

        setLoading(false);
        showToast(`Clima atualizado em ${refreshedWeather.city}, ${refreshedWeather.country}`, 'success');
    }

    return (
        <div 
            ref={containerRef}
            className="relative background-color-2 shadow-md h-dvh rounded-lg p-4 shrink-0 overflow-visible overflow-x-hidden weather-column"
            style={{ width: `${width}%` }}
        >
            {columnsQuantity > 1 && (
                <button
                    type="button"
                    aria-label="Close column"
                    onClick={() => onClose && onClose(id)}
                    className="absolute top-0 right-0 inline-flex items-center justify-center w-8 h-8 rounded-full bg-white border border-gray-200 text-gray-600 hover:bg-indigo-500 hover:text-white shadow-sm focus:outline-none focus:ring-2 focus:ring-indigo-500 close-column-button z-50"
                >
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="w-4 h-4">
                        <path fillRule="evenodd" d="M5.47 5.47a.75.75 0 011.06 0L12 10.94l5.47-5.47a.75.75 0 111.06 1.06L13.06 12l5.47 5.47a.75.75 0 11-1.06 1.06L12 13.06l-5.47 5.47a.75.75 0 11-1.06-1.06L10.94 12 5.47 6.53a.75.75 0 010-1.06z" clipRule="evenodd" />
                    </svg>
                </button>
            )}
            <input
                value={query}
                id={`input-city-query-${id}`}
                onChange={e => handleSearch(e.target.value)}
                onClick={() => handleSearch(document.getElementById(`input-city-query-${id}`)?.getAttribute('value') || '')}
                onKeyDown={e => e.key === 'Escape' && setSuggestions([])}
                className="w-full border border-gray-300 rounded p-2 font-color-1"
                placeholder={t('searchPlaceholder')}
            />

            {suggestions.map(city => (
                <li
                    key={city.lat + city.lon}
                    className="p-2 list-none cursor-pointer list-background-color font-color-1"
                    onClick={() => handleCitySelect(city)}
                >
                    {getCityDisplayName(city)}{city.state ? `, ${city.state}` : ''} - {city.country}
                </li>
            ))}

            {loading && (
                <div className="flex flex-col items-center justify-center my-6">
                    <svg
                        className="animate-spin-slow text-blue-400 drop-shadow-lg"
                        style={{ width: '48px', height: '48px' }}
                        viewBox="0 0 50 50"
                        fill="none"
                        aria-hidden="true"
                    >
                        <circle
                            className="opacity-20"
                            cx="25"
                            cy="25"
                            r="20"
                            stroke="#60A5FA"
                            strokeWidth="6"
                        />
                        <path
                            d="M45 25c0-11.046-8.954-20-20-20"
                            stroke="#2563EB"
                            strokeWidth="6"
                            strokeLinecap="round"
                        >
                            <animateTransform
                                attributeName="transform"
                                type="rotate"
                                from="0 25 25"
                                to="360 25 25"
                                dur="1s"
                                repeatCount="indefinite"
                            />
                        </path>
                    </svg>
                    <span className="mt-2 text-blue-500 font-semibold tracking-wide animate-pulse">{t('loading')}</span>
                </div>
            )}
            {/*
                // Custom animation for slower, smoother spin
                // Add this to your global CSS (e.g., App.css or index.css):
                // .animate-spin-slow { animation: spin 1.2s linear infinite; }
                // @keyframes spin { to { transform: rotate(360deg); } }
            */}


            {(!loading && query && !suggestions.length && query.length < 3) && (
                <li
                    key={query.length}
                    className="p-2 list-none cursor-text list-background-color font-color-1 italic"
                >
                    {t('searchMinChars')}...
                </li>
            )}

            <WeatherDisplay weather={weather} onRefresh={handleRefresh} isLoading={loading} />
        </div>
    );


}