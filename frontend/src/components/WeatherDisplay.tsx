import { useTranslation } from 'react-i18next';
import type { WeatherApiResponse } from "../interfaces/WeatherApiResponse";
import i18n from '../config/i18n';

type Props = {
  weather: WeatherApiResponse | undefined;
  onRefresh?: () => void;
  isLoading?: boolean;
};

export const WeatherDisplay = ({ weather, onRefresh, isLoading }: Props) => {
  const { t } = useTranslation();

  if (!weather) {
    return null;
  }

  const current = weather.current;
  const currentWeather = current.weather[0];

  function capitalizeFirstLetter(string: string) {
    if (!string)
        return "";
    return string.charAt(0).toUpperCase() + string.slice(1);
  }

  return (
    <div className="mt-4 text-sm font-color-1">
        <div className="mb-3">
            <h2 className="text-lg font-bold">
                {weather.city}, {weather.country}
            </h2>
            <div className="flex items-center justify-between grid-cols-[32px_auto_32px] place-items-center">
                <div></div>
                <div>
                    <p className="text-xs text-gray-500">{t('lastUpdated', { ts: new Date(current.dt * 1000).toLocaleString() })}</p>
                </div>
                <div>
                    {onRefresh && (
                        <button
                            onClick={onRefresh}
                            disabled={isLoading}
                            className="ml-2 p-1 rounded hover:bg-gray-600 disabled:opacity-50 disabled:cursor-not-allowed transition-colors"
                            aria-label="Atualizar dados"
                            title="Atualizar dados"
                        >
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="size-6">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M16.023 9.348h4.992v-.001M2.985 19.644v-4.992m0 0h4.992m-4.993 0 3.181 3.183a8.25 8.25 0 0 0 13.803-3.7M4.031 9.865a8.25 8.25 0 0 1 13.803-3.7l3.181 3.182m0-4.991v4.99" />
                            </svg>
                        </button>
                    )}
                </div>
            </div>
        </div>

        {/* Interpretação de IA */}
        {weather.aiInterpretation && (
            <div className="mb-3 p-2 bg-slate-800 rounded border border-blue-600">
                <p className="text-xs font-bold mb-1">{t('aiInterpretationTitle')}</p>
                <p className="text-xs italic text-blue-200">
                    {weather.aiInterpretation}
                </p>
            </div>
        )}

        {/* Clima Atual */}
        <div className="mb-3 p-2 border-l-4 border-blue-500 grid grid-cols-[100px_auto_100px] place-items-center">
            <div>
                <img
                    src={`https://openweathermap.org/img/wn/${currentWeather.icon}@2x.png`}
                    alt="AI Icon"
                />
            </div>
            <div>
                <p className="text-xs font-bold mb-2">{t('currentWeatherTitle')}</p>
                <p className="text-2xl font-semibold">{capitalizeFirstLetter(currentWeather.description)}</p>
                <p className="text-xl font-bold mt-1">{Math.round(current.temp)}°C</p>
                <p className="text-xs">
                    {t('feelsLikeTemp')}: {Math.round(current.feels_like)}°C
                </p>
            </div>
            <div></div>
        </div>

        {/* Detalhes */}
        <div className="grid grid-cols-2 gap-2 text-xs">
            <div>
                <p className="text-gray-400">{t('humidity')}</p>
                <p className="font-semibold">{current.humidity}%</p>
            </div>
            <div>
                <p className="text-gray-400">{t('wind')}</p>
                <p className="font-semibold">{Math.round(current.wind_speed)} m/s</p>
            </div>
            <div>
                <p className="text-gray-400">{t('pressure')}</p>
                <p className="font-semibold">{current.pressure} mb</p>
            </div>
            <div>
                <p className="text-gray-400">{t('clouds')}</p>
                <p className="font-semibold">{current.clouds}%</p>
            </div>
            <div>
                <p className="text-gray-400">{t('uv')}</p>
                <p className="font-semibold">{current.uvi}</p>
            </div>
            <div>
                <p className="text-gray-400">{t('visibility')}</p>
                <p className="font-semibold">
                    {(current.visibility / 1000).toFixed(1)} km
                </p>
            </div>
        </div>

        {/* Alertas */}
        {weather.alerts && weather.alerts.length > 0 && (
            <div className="mt-4 p-2 bg-red-100 border-l-4 border-red-500">
                <h3 className="font-semibold text-red-700 mb-2">
                    {t('weatherAlerts')}:
                </h3>
                {weather.alerts.map((alert, index) => (
                    <div key={index} className="mb-2 text-gray-600">
                        <p className="font-bold">{alert.event}</p>
                        <p className="text-xs">
                            {t('from')} {new Date(alert.start * 1000).toLocaleString()} {t('until')} {new Date(alert.end * 1000).toLocaleString()}
                        </p>
                        <p className="text-xs italic">{alert.description}</p>
                    </div>
                ))}
            </div>
        )}

        {/* Precipitação */}
        {(current.rain || current.snow) && (
            <div className="mt-3 p-2 bg-gray-50 rounded text-xs text-blue-900">
                {current.rain && <p>{t('rainPercent')} (1h): {current.rain["1h"]} mm</p>}
                {current.snow && <p>{t('snow')} (1h): {current.snow["1h"]} mm</p>}
            </div>
        )}

        {/* Próximas horas */}
        {weather.hourly && weather.hourly.length > 0 && (
            <div className="overflow-x-auto mt-4">
                <h3 className="font-semibold mb-2">{t('nextHours')}</h3>
                <div className="flex gap-4">
                    {weather.hourly.slice(0, 12).map((hour) => {
                        const hourWeather = hour.weather[0];
                        const date = new Date(hour.dt * 1000);
                        const hours = date.getHours().toString().padStart(2, "0");
                        const minutes = date.getMinutes().toString().padStart(2, "0");
                        return (
                            <div
                                key={hour.dt}
                                className="min-w-20 p-2 background-color-3 rounded text-center"
                            >
                                <p className="text-xs mb-1">
                                    {hours}:{minutes}
                                </p>
                                <img
                                    src={`https://openweathermap.org/img/wn/${hourWeather.icon}@2x.png`}
                                    alt={capitalizeFirstLetter(hourWeather.description)}
                                    className="mx-auto"
                                />
                                <p className="text-sm font-bold">{Math.round(hour.temp)}°C</p>
                                <p className="text-xs text-gray-600">{hourWeather.main}</p>
                                <p className="text-xs">
                                    {t('rainPercent')}: {Math.round((hour.pop || 0) * 100)}%
                                </p>
                                {hour.rain && (
                                    <p className="text-xs text-blue-300">
                                        ({hour.rain["1h"]} mm)
                                    </p>
                                )}
                            </div>
                        );
                    })}
                </div>
            </div>
        )}

        {/* Próximos dias */}
        {weather.daily && weather.daily.length > 0 && (
            <div className="mt-4">
                <h3 className="font-semibold mb-2">{t('nextDays')}</h3>
                <div className="flex flex-col gap-2">
                    {weather.daily.slice(0, 7).map((day) => {
                        const dayWeather = day.weather[0];
                        const date = new Date(day.dt * 1000);
                        const dayName = date.toLocaleDateString(i18n.language, { weekday: "long" });
                        return (
                            <div
                                key={day.dt}
                                className="p-2 background-color-3 rounded grid grid-cols-[1fr_50px_50px_50px_50px] items-center"
                            >
                                <p className="capitalize">{dayName}</p>
                                <img
                                    src={`https://openweathermap.org/img/wn/${dayWeather.icon}@2x.png`}
                                    alt={capitalizeFirstLetter(dayWeather.description)}
                                    className="w-8 h-8"
                                />
                                <p className="text-sm font-bold">{Math.round(day.temp.min)}°C</p>
                                <p className="text-sm font-bold">{Math.round(day.temp.max)}°C</p>
                                <p className="text-xs">
                                    {t('rainPercent')}: {Math.round((day.pop || 0) * 100)}%
                                </p>
                            </div>
                        );
                    })}
                </div>
            </div>
        )}
    </div>
  );
};
