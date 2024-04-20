
FROM bellsoft/liberica-openjdk-alpine:17
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]





# build
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install 
COPY . .
RUN npm run build

# nginx
FROM nginx:stable-alpine as production-stage
COPY ./nginx/default /etc/nginx/sites-available/default
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]







