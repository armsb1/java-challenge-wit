package com.andre.dev.java_challenge_wit;

import com.andre.dev.java_challenge_wit.service.CalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * The type Calculator runner.
 */
@Component
@AllArgsConstructor
public class CalculatorRunner implements CommandLineRunner {

    /**
     * The Calculator service.
     */
    CalculatorService calculatorService;
    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nInsira um cálculo simples que deseja realizar (ex: 1+2 ou 4-3 ou 2*3 ou 9/3) se quiser terminar escreva SAIR\n");
            String input = scanner.nextLine();

            if (input.equals("SAIR")) {
                System.out.println("Encerrou a calculadora. Para fazer novos cálculos tem de reiniciar o módulo\n");
                break;
            }
            BigDecimal result = calculatorService.calculate(input);
            System.out.println("O Resultado para: " + input + " é " + result);
        }

    }
}
