package com.andre.dev.java_challenge_wit.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The type Calculations service.
 */
@Service
@AllArgsConstructor
@Slf4j
public class CalculationsService {

    /**
     * Calculate sum big decimal.
     *
     * @param a the a
     * @param b the b
     * @return the big decimal
     */
    public BigDecimal calculateSum(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    /**
     * Calculate subtraction big decimal.
     *
     * @param a the a
     * @param b the b
     * @return the big decimal
     */
    public BigDecimal calculateSubtraction(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    /**
     * Calculate multiplication big decimal.
     *
     * @param a the a
     * @param b the b
     * @return the big decimal
     */
    public BigDecimal calculateMultiplication(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    /**
     * Calculate division big decimal.
     *
     * @param a the a
     * @param b the b
     * @return the big decimal
     */
    public BigDecimal calculateDivision(BigDecimal a, BigDecimal b) {
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            log.error("Attempted division by zero: {} / {}", a, b);
            throw new IllegalArgumentException("Denominador não pode ser 0");
        }
        return a.divide(b, 2, RoundingMode.HALF_UP);
    }

}
