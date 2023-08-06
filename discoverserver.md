## Discovery Server

### Role for configuring properties in microservice:
1. Removing enviornment setting from compiled code. Putting in resource folder , for every change we have to buid.
2. Changing Runtime behavior
3. Property consistent across server
4. Cache value to reduce load on DB.


### Challenges if we keep properties in application
1. Local config may fall out of sync
2. No history of change env
3. Config change require redeployment and restart
4. Incosistent across teams.

### Enviornment Repositroy
Store enviornment object. Parameterized by 3 variable
1. {application}-> map to  spring.application.name
2. {profile} -> map to pring.profiles.active. Default value as default.
3. {label} -> which is a server side feature labelling a "versioned" set of config files
Label in Git -> If the git branch or tag name contains a slash (/), then the label in the HTTP URL should instead be specified with the special string (_)
```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/codesnaper/sample-microservice
#          want to skip ssl validation
          skipSslValidation: true
#          time out
          timeout: 4
```


### Pattern matching & multiple repositories
We can have multiple pattern & repositories
Ex: If i have 2 repository with 2 different configuration repos and want to server.</br>
1st repo contain configuraiton for 2 application -> app1 , app2 
2nd repo contain another configuration of application -> app3 </br>

```text
https://github.com/repo1
    |- config
        |- app1
            |- app1-dev.yaml
        |- app2
            |- app2-dev.yaml
https://github.com/repo1
    |- config
        |- app3
            |- app3-dev.yaml

```

```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: 
          repos:
            repo1:
              uri: https://github.com/repo1
              cloneOnStart: true
              pattern: repo1*
#              Used to search properties on below path and load
              search-paths:
                - config/app1*
                - config/app2*
            repo2:
              uri: https://github.com/repo2
              cloneOnStart: true
              pattern: repo2*
              search-paths:
                - config/app1*
                - config/app2*
```

### Spring cloud adding encryption and decryption
1. Adding key or Keystore 
```yaml
encrypt:
  key: dshjhdsjhjdshjds
``` 
```yaml
#Create JKS With privatekey entry alias type
encrypt:
  keyStore:
    location: classpath:jks/encrypt/encryptkey.jks
    password: changeit
    alias: echangeit
```
2. Spring boot will enable 2 url - /encrypt and /decrypt.
3. for decrypting propeties in application properties"
```yaml
scope: '{cipher}AQBc3O+kFC7pCsaC/H5YDPjP29/LLP6VM3B9ACXNx7hOk3oj4YcLJM1epbTgR9kqqsBiPnqYib1ktRMEpSosqr5snmerQE9AaJSeKuqLB34VZwq9hhw7+tHJGhxMszQkwUf9Mxsf7YhKmH1yfMlfiybDdj1wrPLerlbyOTMX7xlDfuD+IxfNxlka/xW/OnNwQMz51CLOkic92ECoHuf1HN7XrouvCcxUzDhGYZ7R89p/XysAJHKB1FvwGlKjPIWU31hkAk/EZXc0uAk5ir9Q0asiJ3uD2hAQhhSBzsxrQLZhUHLvZ+hnQPS+T38vpcp1P9jtYQYVstYrTlCA9ysMl1xQQf6syC7+RmHYfFoBTamhPQ5PpkIyQP1VGqA8MiTlnfA='
``` 
when spring config load it will decrypt and load properties

### Serving Plain Text
to server file url: /{application}/{profile}/{label}/{path} </br>
where path is  path to a file name (such as log.xml)

### Push Notification (Change property at fly)


## Discovery Client:
