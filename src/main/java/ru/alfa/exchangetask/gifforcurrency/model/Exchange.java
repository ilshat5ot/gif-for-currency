package ru.alfa.exchangetask.gifforcurrency.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class Exchange {

    private String base;
    private Map<String, Double> rates;

    public Exchange(Exchange exchange) {
        this.base = exchange.base;
        this.rates = new LinkedHashMap<>(exchange.getRates());
    }
}
