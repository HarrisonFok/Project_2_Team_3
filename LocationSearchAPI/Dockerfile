FROM openjdk:8
ARG JAR_FILE
COPY ${JAR_FILE} /tmp
WORKDIR /tmp
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "LocationSearchAPI.jar"]