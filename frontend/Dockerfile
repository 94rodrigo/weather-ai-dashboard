# Stage 1: build
FROM node:20-bullseye AS build
WORKDIR /app

COPY package.json package-lock.json* ./
COPY yarn.lock* ./

RUN npm ci

COPY . .
RUN npm run build

# Stage 2: runtime
FROM nginx:1.25-alpine
COPY --from=build /app/dist /usr/share/nginx/html

# Ajuste para fallback de SPA
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d/app.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]