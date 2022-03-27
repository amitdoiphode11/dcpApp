FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} dcb-app.jar
ENTRYPOINT ["java","-jar","/dcb-app.jar"]
EXPOSE 8082