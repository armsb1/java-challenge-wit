package com.andre.dev.java_challenge_wit.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Calculator service.
 */
@Service
@AllArgsConstructor
@Slf4j
public class CalculatorService {

    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String BASE_URL = "http://localhost:8080/api/v1/calculations";

    /**
     * Calculate big decimal.
     *
     * @param equation the equation
     * @return the big decimal
     */
    public BigDecimal calculate(String equation) {
        log.info("Starting calculation for equation: {}", equation);

        Map<String, String> operationEndpoints = new HashMap<>();
        operationEndpoints.put("+", "/sum");
        operationEndpoints.put("-", "/subtraction");
        operationEndpoints.put("*", "/multiplication");
        operationEndpoints.put("/", "/division");

        String operator = operationEndpoints.keySet().stream()
                .filter(equation::contains)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Operador inválido ou não encontrado na equação"));

        String[] numbers = equation.split(String.format("\\%s", operator));
        if (numbers.length != 2) {
            log.error("Invalid equation: {} - must contain exactly two numbers", equation);
            throw new IllegalArgumentException("Equação inválida: deve conter apenas 2 números");
        }


        BigDecimal a = new BigDecimal(numbers[0]);
        BigDecimal b = new BigDecimal(numbers[1]);

        String endpoint = operationEndpoints.get(operator);
        String url = String.format("%s%s?a=%s&b=%s", BASE_URL, endpoint, a, b);

        log.info("Calling REST API: {}", url);
        BigDecimal result = restTemplate.getForObject(url, BigDecimal.class);

        String message = endpoint.substring(1, 2).toUpperCase() + endpoint.substring(2) + ":" + result;
        kafkaTemplate.send("calculations", message);
        log.info("Message sent to Kafka: {}", message);

        return result;
    }
}
