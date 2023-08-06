FROM eclipse-temurin:17-jdk-alpine
RUN addgroup -S appgroup && adduser -S user -G appgroup
USER user
VOLUME /tmp
ENV CONFIG_USER=root
ENV EUREKA_URL_ZONE1=https://172.29.16.1:9448/eureka/
ENV CONFIG_PASSWORD=root
ENV SSL_KEYSTORE_PASSWORD=changeit
ENV ENCRYPT_KEYSTORE_PASSWORD=changeit
ENV ENCRYPT_KEY_PASSWORD=changeit
ENV COFIG_TRUSTSTORE_PASSWORD=changeit
ENV DISCOVERY_TRUSTSTORE_PASSWORD=changeit
ENV HOST_NAME=172.29.16.1
ARG JAVA_OPTS=-Ddebug -Xmx128m
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]
