# Weather Dashboard

Weather Dashboard is a full-stack web application that provides real-time weather information and AI-powered insights for multiple locations. The project is built with a modern technology stack, supporting multiple languages for a global user experience.

<img width="2560" height="1080" alt="weather-ai-1" src="https://github.com/user-attachments/assets/5d7261b4-1997-454b-8698-45a89e085e7c" />


## Technologies Used

- **Backend:** Java 25 (Spring Boot)
- **Frontend:** React, TypeScript, Vite
- **Internationalization:** Multi-language support (English, Portuguese, Spanish, French, German)
- **APIs:**
  - [OpenWeather API](https://openweathermap.org/api) for weather data
  - [OpenAI API](https://platform.openai.com/docs/api-reference) for AI-powered features

## Features

- Search and display weather data for multiple locations
- AI-generated weather insights and recommendations
- Add and manage multiple weather columns
- Responsive and modern UI
- Toast notifications and dialogs
- Language selection dropdown

## Getting Started

### Prerequisites

- [Node.js](https://nodejs.org/) (for the frontend)
- [Java 25](https://jdk.java.net/25/) (for the backend)
- [Maven](https://maven.apache.org/) (for the backend)

### Environment Variables

You need API keys for OpenWeather and OpenAI. Copy the `.env.example` file in the `backend` folder to `.env` and fill in your API keys:

```sh
cp backend/.env.example backend/.env
```

Edit `backend/.env` and set the following variables:

- `OPENWEATHER_API_KEY`
- `OPENAI_API_KEY`

### Running the Backend

1. Navigate to the `backend` directory:
   ```sh
   cd backend
   ```
2. Build and run the Spring Boot application:
   ```sh
   ./mvnw spring-boot:run
   ```
   Or on Windows:
   ```sh
   mvnw.cmd spring-boot:run
   ```

### Running the Frontend

1. Navigate to the `frontend` directory:
   ```sh
   cd frontend
   ```
2. Install dependencies:
   ```sh
   npm install
   ```
3. Start the development server:
   ```sh
   npm run dev
   ```

The frontend will be available at [http://localhost:5173](http://localhost:5173) by default.

## Internationalization

The application supports multiple languages. You can select your preferred language from the dropdown in the UI. Translation files are located in `frontend/src/config/labels/`.

## License

This project is for educational and demonstration purposes.
