package ru.alfa.exchangetask.gifforcurrency.service.main;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface GifForCurrencyService {

    ResponseEntity<Map> getGifForCurrency(String currency);

    ResponseEntity<List> getRates();
}
