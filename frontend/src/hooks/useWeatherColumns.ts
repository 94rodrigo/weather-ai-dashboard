import { useState, useEffect } from "react";
import type { WeatherApiResponse } from "../interfaces/WeatherApiResponse";

export type WeatherColumn = {
  id: string;
  query: string;
  weather?: WeatherApiResponse;
};

const STORAGE_KEY = "weatherColumns";

export function useWeatherColumns() {
  const [columns, setColumns] = useState<WeatherColumn[]>([
    { id: crypto.randomUUID(), query: "" },
  ]);
  const [isLoaded, setIsLoaded] = useState(false);

  // Carregar dados do localStorage ao montar o componente
  useEffect(() => {
    const savedColumns = localStorage.getItem(STORAGE_KEY);
    if (savedColumns) {
      try {
        setColumns(JSON.parse(savedColumns));
      } catch (error) {
        console.error("Erro ao carregar dados do localStorage:", error);
      }
    }
    setIsLoaded(true);
  }, []);

  // Salvar dados no localStorage sempre que columns mudar
  useEffect(() => {
    if (isLoaded) {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(columns));
    }
  }, [columns, isLoaded]);

  function addColumn() {
    if (columns.length >= 5)
        return;

    setColumns([...columns, { id: crypto.randomUUID(), query: "" }]);
  }

  function updateQuery(id: string, query: string) {
    setColumns(cols => cols.map(col => (col.id === id ? { ...col, query } : col)));
  }

  function setWeather(id: string, weather: any) {
    setColumns(cols => cols.map(col => (col.id === id ? { ...col, weather } : col)));
  }

  function removeColumn(id: string) {
    setColumns(cols => cols.filter(col => col.id !== id));
  }

  return {
    columns,
    addColumn,
    updateQuery,
    setWeather,
    removeColumn,
  };
};