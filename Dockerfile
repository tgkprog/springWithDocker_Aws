FROM amazoncorretto:17
VOLUME /tmp
VOLUME /data
ARG JAR_FILE
COPY build/libs/ContactMe-1.jar /data/app.jar
ENTRYPOINT ["java","-jar","/data/app.jar"]
