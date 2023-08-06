### Network Layer (OSI Model)
There are 7 layer</br>
    **application layer**  -> (Application access netowork service)     -> **_HTTP_** and **_HTTPS_** </br>
    **Presentation layer** -> (Data is in usable format & data encryption occur)</br>
    **Session Layer**      -> (Maintain connection and responsible for controlling port)</br>
    **Transport layer**   -> (ensure that the bits and bytes are transferred properly) -> **_TCP, TLS, UDP_**</br>
    **Network Layer**     -> (responsible for transferring bits and bytes)</br>
    **Data Layer**        -> (define format of data on network)</br>
    **Physical Layer**    -> (transmit raw bit strem over physical medium)</br>

### HTTPS & Http:
HTTPS utilises a combination of HTTP and TLS/SSL protocols to establish secure communication channels between clients and servers
HTTP and HTTPS operate on top of a Transport Layer protocol (TCP or UDP) and are responsible for managing data communication. HTTP is unencrypted, whereas HTTPS is encrypted.

### User Datagram Protocol(UDP):
- fast and simple protocol for sending data packets over the internet.
- packets may be lost or received out of order

### TCP:
- reliable, connection-oriented protocol responsible for ensuring data delivery
- creates a virtual connection to ensure that packets arrive in the correct order and completeness
- sets up a connection via a three-way handshake and uses sequencing to make sure that all data arrives accurately and in the correct order.

### TLS
- TLS (Transport Layer Security) operates between the Transport layer (TCP) and the Application layer (HTTP/HTTPS).
- encrypts data transmitted between a client and server. 
- also Provide authentication, integrity checks, helping ensure that you are communicating with the intended party without data having been altered during transit. 

## Steps to generate self-signed PKCS#12 SSL certificate and export its keys:

1. Create PKCS#12 keystore (.p12 or .pfx file)
```shell
    keytool -genkeypair -keystore myKeystore.p12 -storetype PKCS12 -storepass MY_PASSWORD -alias KEYSTORE_ENTRY -keyalg RSA -keysize 2048 -validity 99999 -dname "CN=My SSL Certificate, OU=My Team, O=My Company, L=My City, ST=My State, C=SA" -ext san=dns:mydomain.com,dns:localhost,ip:127.0.0.1
```
- `myKeystore.p12` = keystore filename. It can with .pfx extension as well.
- `MY_PASSWORD` = password used for the keystore and the private key as well.
- `CN` = commonName, it will be shown as certiciate name in certificates list. (Java use CN to match certificate)
- `OU` = organizationUnit, department name for example.
- `O` = organizationName, the company name.
- `L` = localityName, the city.
- `S` = stateName, the state.
- `C` = country, the 2-letter code of the country.

> Note: This step can be done using openssl but it's more complicated.

2. Create the public certificate:
 - Using Keytool:
```shell
keytool -exportcert -keystore myKeystore.p12 -storepass MY_PASSWORD -alias KEYSTORE_ENTRY -rfc -file public-certificate.pem
```
 - Using openssl
```shell
    openssl pkcs12 -in myKeystore.p12 -password pass:MY_PASSWORD -nokeys -out public-certificate.pem
```

3. Extract Private key from PKCS12:
```shell
    openssl pkcs12 -in myKeystore.p12 -password pass:MY_PASSWORD -nodes -nocerts -out private-key.key
```

4. Export public key from private:
```shell
openssl rsa -in private-key.key -pubout > public-key.pub
```
5. Import cert into jks
```shell
keytool -importcert -keystore server.truststore -alias "cert" -file public-certificate.pem
```
6. Import PKCS12 into jks
```shell
keytool -importkeystore \
  -deststorepass storepassword \
  -destkeypass keypassword \
  -destkeystore my-keystore.jks \
  -srckeystore cert-and-key.p12 \
  -srcstoretype PKCS12 \
  -srcstorepass p12password \
  -alias 1
```

### Spring boot Enabling HTTPS
- create PKCS12 or keystore with private key and certificate.
- Add below properties:
```yaml
server:
  ssl:
#    TO make ssl enabled
    enabled: true 
#    Type: PKCS12, JKS
    key-store-type: PKCS12 
    key-store: classpath:jks/auth-client/serverkey.p12
    key-store-password: changeit
    protocol: TLS
    alias: app
    enabled-protocols: TLSv1.2
``` 
- we can add jks and pem bundle and refer:
1. Create Bundle:
```yaml
spring:
  ssl:
    bundle:
      jks:
        server-1:
          key:
            alias: "server"
          keystore:
#            We can add PKCS12 or JKS
            location: "classpath:server.p12" 
            password: "secret"
            type: "PKCS12"
        server-2:
          key:
            alias: "server"
          keystore:
            location: "classpath:server.p12"
            password: "secret"
            type: "PKCS12"
      pem:
        client1:
          truststore:
            certificate: "classpath:client.crt"
        client2:
          truststore:
            certificate: "classpath:client.crt"
``` 
2. Refer Bundle:
```yaml
server:
  ssl:
    bundle: “server1”
    client-auth: NEED
``` 
Example:
[Discovery Server Example](/discovery-server/src/main/resources/application.yaml)

