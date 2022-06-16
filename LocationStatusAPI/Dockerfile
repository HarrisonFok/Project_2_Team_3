FROM openjdk:8
ARG JAR_FILE
COPY ${JAR_FILE} /tmp
WORKDIR /tmp
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "LocationStatusAPI.jar"]