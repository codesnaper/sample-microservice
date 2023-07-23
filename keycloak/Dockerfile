FROM quay.io/keycloak/keycloak:latest
EXPOSE 80:8080
ENV KC_HOSTNAME=localhost
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=password
ENTRYPOINT ["sh", "-c", "/opt/keycloak/bin/kc.sh start-dev"]
