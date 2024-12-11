package com.andre.dev.java_challenge_wit;

import com.andre.dev.java_challenge_wit.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {

    private static final String rootEndpoint = "http://localhost:8080/api/v1/calculations";
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private CalculatorService calculatorService;


    @Test
    void testCalculateSumOK1() {

        BigDecimal mockResult = new BigDecimal("10");
        when(restTemplate.getForObject(rootEndpoint + "/sum?a=5&b=5", BigDecimal.class))
                .thenReturn(mockResult);

        BigDecimal result = calculatorService.calculate("5+5");

        assertEquals(mockResult, result);
        verify(kafkaTemplate).send("calculations", "Sum:10");
    }

    @Test
    void testCalculateSubtractionOK1() {
        BigDecimal mockResult = new BigDecimal("3");
        when(restTemplate.getForObject(rootEndpoint + "/subtraction?a=5&b=2", BigDecimal.class))
                .thenReturn(mockResult);

        BigDecimal result = calculatorService.calculate("5-2");

        assertEquals(mockResult, result);
        verify(kafkaTemplate).send("calculations", "Subtraction:3");
    }

    @Test
    void testCalculateMultiplicationOK1() {
        BigDecimal mockResult = new BigDecimal("20");
        when(restTemplate.getForObject(rootEndpoint + "/multiplication?a=4&b=5", BigDecimal.class))
                .thenReturn(mockResult);

        BigDecimal result = calculatorService.calculate("4*5");

        // Assertions
        assertEquals(mockResult, result);
        verify(kafkaTemplate).send("calculations", "Multiplication:20");
    }

    @Test
    void testCalculateDivisionOK1() {
        BigDecimal mockResult = new BigDecimal("2");
        when(restTemplate.getForObject(rootEndpoint + "/division?a=10&b=5", BigDecimal.class))
                .thenReturn(mockResult);

        BigDecimal result = calculatorService.calculate("10/5");

        assertEquals(mockResult, result);
        verify(kafkaTemplate).send("calculations", "Division:2");
    }

    @Test
    void testInvalidEquationTooManyOperandsNOK1() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.calculate("5+5+5");
        });

        assertEquals("Equação inválida: deve conter apenas 2 números", thrown.getMessage());
    }

    @Test
    void testInvalidEquationNoOperatorNOK2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.calculate("5 5");
        });

        assertEquals("Operador inválido ou não encontrado na equação", thrown.getMessage());
    }

    @Test
    void testInvalidOperatorNOK3() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.calculate("5^5");
        });

        assertEquals("Operador inválido ou não encontrado na equação", thrown.getMessage());
    }
}


