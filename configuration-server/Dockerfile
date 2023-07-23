FROM eclipse-temurin:17-jdk-alpine
RUN addgroup -S appgroup && adduser -S user -G appgroup
USER user
VOLUME /tmp
ENV CONFIG_USER=root
ENV CONFIG_PASSWORD=root
ARG JAVA_OPTS=-Ddebug -Xmx128m
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]
