FROM openjdk:8-alpine

COPY target/uberjar/travel-diary.jar /travel-diary/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/travel-diary/app.jar"]
