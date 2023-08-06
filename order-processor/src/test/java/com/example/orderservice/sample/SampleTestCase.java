package com.example.orderservice.sample;

import com.example.orderservice.OrderServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ActiveProfiles;

import java.nio.charset.StandardCharsets;

@SpringBootTest(classes = OrderServiceApplication.class)
@Import({SampleConfiguration.class, TestChannelBinderConfiguration.class})
@ActiveProfiles("test")
public class SampleTestCase {

    @Autowired
    private InputDestination input;

    @Autowired
    private OutputDestination output;

    @Autowired
    SampleConfiguration sampleConfiguration;

    @Autowired
    StreamBridge streamBridge;

    @Test
    void testSampleFunction() {
        input.send(new GenericMessage<>("hello".getBytes(StandardCharsets.UTF_8)), "test-in");
        Assertions.assertEquals("HELLO", new String(output.receive(100, "test-out").getPayload()));
    }


    @Test
    void testSupplier(){
        sampleConfiguration.getName();
        Assertions.assertEquals("SHUBHAM", new String(output.receive(100, "name-test-out").getPayload()));
    }

    @Test
    void consumerMessageTest() throws InterruptedException {
        input.send(new GenericMessage<>("hello".getBytes(StandardCharsets.UTF_8)), "consume-in-topic");
        Thread.sleep(1000);
        Assertions.assertNull(output.receive(100, "consume-in-topic"));
    }

    @Test
    void testFunctionComposition(){
        input.send(new GenericMessage<>("hello".getBytes(StandardCharsets.UTF_8)), "enrich-test-in");
        Assertions.assertEquals("HELLO_payload", new String(output.receive(100, "enrich-test-out").getPayload()));

    }

    @Test
    void multipleInSingleOut(){
        input.send(new GenericMessage<>("hello".getBytes(StandardCharsets.UTF_8)), "test-in-1");
        input.send(new GenericMessage<>("how".getBytes(StandardCharsets.UTF_8)), "test-in-2");
        Assertions.assertEquals("HELLO_payload", new String(output.receive(100, "test-out-1").getPayload()));
    }
}
