FROM eclipse-temurin:11
RUN mkdir /opt/app
COPY target/takehome-1.0-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/takehome-1.0-SNAPSHOT.jar"]

EXPOSE 8080