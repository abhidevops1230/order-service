package com.enterprise.platform.orders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnOrder() {

        var response = restTemplate.getForEntity(
                "/api/orders/ORD-1001",
                String.class
        );

        assertThat(response.getStatusCode().is2xxSuccessful())
                .isTrue();

        assertThat(response.getBody())
                .contains("ORD-1001");
    }
}