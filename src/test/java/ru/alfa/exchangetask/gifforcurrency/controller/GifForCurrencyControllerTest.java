package ru.alfa.exchangetask.gifforcurrency.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import ru.alfa.exchangetask.gifforcurrency.service.main.GifForCurrencyServiceImpl;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GifForCurrencyController.class)
class GifForCurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GifForCurrencyServiceImpl service;

    @Test
    public void testGetRates() throws Exception {
        ResponseEntity<Map> responseEntity = ResponseEntity
                .ok(Map.of("base", "USD", "rates",
                        Map.of("RUB",70.04, "EUR", 1.25)));
        when(service.getGifForCurrency("RUB")).thenReturn(responseEntity);

        mockMvc.perform(get("/currency/RUB"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.base").value("USD"))
                .andExpect(jsonPath("$.rates.RUB").value(70.04));


    }

    @Test
    public void testGetCurrency() throws Exception {

        ResponseEntity<List> listRates = ResponseEntity.ok(List.of("RUB", "EUR", "CAD"));
        when(service.getRates()).thenReturn(listRates);

        mockMvc.perform(get("/currency/get-rates"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("RUB"));
    }
}