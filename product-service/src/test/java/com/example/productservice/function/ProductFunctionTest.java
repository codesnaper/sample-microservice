package com.example.productservice.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@FunctionalSpringBootTest
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductFunctionTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void getAllProduct() throws URISyntaxException {
        ResponseEntity<String> result = this.rest.exchange(
                RequestEntity.get(new URI("/functionRouter"))
                        .header("X-FUNCTION-NAME","getAllProduct1")
                        .header("Accept","*/*")
                        .build(), String.class);
        Assertions.assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), result.getStatusCode());
    }
}