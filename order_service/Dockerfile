FROM maven:3.8.4-openjdk-19 AS MAVEN_BUILD
COPY ./ ./
#RUN mvn clean package -Dmaven.test.skip=true
FROM openjdk:19
COPY --from=MAVEN_BUILD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]