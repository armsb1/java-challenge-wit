package com.andre.dev.java_challenge_wit.controller;

import com.andre.dev.java_challenge_wit.service.CalculationsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * The type Calculations controller.
 */
@RestController()
@RequestMapping("api/v1/calculations")
@AllArgsConstructor
public class CalculationsController {

    private final CalculationsService calculationsService;

    /**
     * Sum big decimal.
     *
     * @param a the a
     * @param b the b
     * @return the big decimal
     */
    @GetMapping("/sum")
    public BigDecimal sum (@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return calculationsService.calculateSum(a, b);
    }

    /**
     * Subtraction big decimal.
     *
     * @param a the a
     * @param b the b
     * @return the big decimal
     */
    @GetMapping("/subtraction")
    public BigDecimal subtraction (@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return calculationsService.calculateSubtraction(a, b);
    }

    /**
     * Multiplication big decimal.
     *
     * @param a the a
     * @param b the b
     * @return the big decimal
     */
    @GetMapping("/multiplication")
    public BigDecimal multiplication (@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return calculationsService.calculateMultiplication(a, b);
    }

    /**
     * Division big decimal.
     *
     * @param a the a
     * @param b the b
     * @return the big decimal
     */
    @GetMapping("/division")
    public BigDecimal division (@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return calculationsService.calculateDivision(a, b);
    }

}
