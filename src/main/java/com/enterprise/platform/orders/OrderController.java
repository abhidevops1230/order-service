package com.enterprise.platform.orders;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @GetMapping("/{orderId}")
    public Map<String, Object> getOrder(@PathVariable String orderId) {

        return Map.of(
                "orderId", orderId,
                "status", "CONFIRMED",
                "service", "order-service"
        );
    }
}