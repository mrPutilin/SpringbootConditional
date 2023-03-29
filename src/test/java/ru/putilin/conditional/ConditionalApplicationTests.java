package ru.putilin.conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    final private static GenericContainer<?> devapp = new GenericContainer<>("devapp");
    final private static GenericContainer<?> prodapp = new GenericContainer<>("prodapp");


    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();

    }

    @Test
    void contextLoads() {
        int devPort = devapp.getMappedPort(8080);
        int prodPort = prodapp.getMappedPort(8081);

        String dev = "Current profile is dev";
        String prod = "Current profile is production";

        String devResponse = restTemplate.getForEntity("http://localhost:" + devPort + "/profile", String.class).getBody();
        String prodResponse = restTemplate.getForEntity("http://localhost:" + prodPort + "/profile", String.class).getBody();

        Assertions.assertEquals(dev, devResponse);
        Assertions.assertEquals(prod, prodResponse);


    }

}
