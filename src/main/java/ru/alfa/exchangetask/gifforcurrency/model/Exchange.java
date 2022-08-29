package ru.alfa.exchangetask.gifforcurrency.model;

import lombok.Data;
import java.util.Map;

@Data
public class Exchange {

    private final String base;
    private final Map<String,Double> rates;
}
