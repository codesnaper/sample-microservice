FROM eclipse-temurin:17-jdk-alpine
RUN addgroup -S appgroup && adduser -S user -G appgroup
USER user
VOLUME /tmp
ENV CONFIG_SERVER_URI=configserver:https://localhost:9445
ENV CONFIG_USER=root
ENV CONFIG_PASSWORD=root
ENV KEY_STORE_PASSWORD=changeit
ARG JAVA_OPTS=-Ddebug -Xmx128m
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]
