package com.example.orderservice.sample;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@TestConfiguration
public class SampleConfiguration {

    @Bean
    public Supplier<String> getName() {
        return () -> "SHUBHAM";
    }

    @Bean
    public Function<String, String> uppercase() {
        return String::toUpperCase;
    }

    @Bean
    public Consumer<String> consumer() {
        return s -> {
            System.out.println("Consumer Message >> " + s);
        };
    }

    @Bean
    public Function<String, String> enrichMessagePayload() {
        return s -> s.concat("_payload");
    }

    @Bean
    public Function<Tuple2<Flux<String>, Flux<String>>, Flux<String>> multipleInSingleOut() {
        return tuple -> Flux.merge(tuple.getT1(), tuple.getT2());
    }
}
