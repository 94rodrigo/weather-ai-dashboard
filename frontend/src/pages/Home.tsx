import { AddColumnButton } from "../components/AddColumnButton";
import { WeatherColumn } from "../components/WeatherColumn";
import { useWeatherColumns } from "../hooks/useWeatherColumns";
import { useEffect } from "react";
import { getCompleteWeather } from "../services/weatherApi";

export default function Home() {
    const { columns, addColumn, updateQuery, setWeather, removeColumn } = useWeatherColumns();

    // Listen to custom event to refresh all columns
    useEffect(() => {
        async function handleRefreshAll() {
            // For each column that has weather (lat/lon), re-fetch and update
            for (const col of columns) {
                if (col.weather && typeof col.weather.lat === 'number' && typeof col.weather.lon === 'number') {
                    try {
                        const refreshed = await getCompleteWeather(col.weather.lat, col.weather.lon);
                        setWeather(col.id, refreshed);
                        console.log(refreshed);
                    } catch (err) {
                        console.error('Error refreshing column', col.id, err);
                    }
                }
            }
        }

        window.addEventListener('refreshAllWeather', handleRefreshAll as EventListener);
        return () => window.removeEventListener('refreshAllWeather', handleRefreshAll as EventListener);
    }, [columns, setWeather]);
    
    // Calcula a largura de cada WeatherColumn: (4/5) / número de colunas
    let weatherColumnWidth = columns.length > 0 ? (80 / columns.length) : 80;
    if (columns.length >= 5) {
        weatherColumnWidth = 20;
    }
    // AddColumnButton sempre ocupa 1/5 (20%)
    const addButtonWidth = 20;

    return (
        <div className="flex gap-4 p-6 h-screen ">
            {columns.map(col => (
                <WeatherColumn
                    key={col.id}
                    id={col.id}
                    query={col.query}
                    weather={col.weather}
                    onQueryChange={updateQuery}
                    onWeatherChange={setWeather}
                    onClose={removeColumn}
                    columnsQuantity={columns.length}
                    width={weatherColumnWidth}
                />
            ))}

            {columns.length < 5 && (
                <AddColumnButton
                    onAdd={addColumn}
                    disabled={columns.length >= 5}
                    width={addButtonWidth}
                />
            )}
        </div>
    );
}