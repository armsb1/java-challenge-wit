package com.andre.dev.java_challenge_wit;

import com.andre.dev.java_challenge_wit.controller.CalculationsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

@SpringBootTest
public class CalculationsControllerTest {

    @Autowired
    private CalculationsController calculationsController;
    private MockMvc mockMvc;
    private final String rootEndpoint = "http://localhost:8080/api/v1/calculations";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(calculationsController).build();
    }
    @Test
    public void testSumOK1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(rootEndpoint + "/sum")
                .param("a", BigDecimal.TEN.toString())
                .param("b", BigDecimal.TEN.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(new BigDecimal(20).toString()));
    }

    @Test
    public void testSubtractionOK1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(rootEndpoint + "/subtraction")
                .param("a", new BigDecimal("10").toString())
                .param("b", new BigDecimal("3").toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(new BigDecimal("7").toString()));
    }
    @Test
    public void testMultiplicationOK1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(rootEndpoint + "/multiplication")
                .param("a", BigDecimal.TEN.toString())
                .param("b", BigDecimal.TEN.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(new BigDecimal("100").toString()));
    }

    @Test
    public void testDivisionOK1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(rootEndpoint + "/division")
                .param("a", BigDecimal.TEN.toString())
                .param("b", new BigDecimal(2).toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(new BigDecimal("5.00").toString()));
    }

}
