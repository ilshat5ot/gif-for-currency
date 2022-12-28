package ru.alfa.exchangetask.gifforcurrency.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfa.exchangetask.gifforcurrency.service.main.GifForCurrencyService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/currency")
public class GifForCurrencyController {

    private final GifForCurrencyService mixGifExchangeService;

    public GifForCurrencyController(GifForCurrencyService mixGifExchangeService) {
        this.mixGifExchangeService = mixGifExchangeService;
    }

    @GetMapping("/{currency}")
    public ResponseEntity<Map> get(@PathVariable String currency){
        return mixGifExchangeService.getGifForCurrency(currency);
    }

    @RequestMapping("/get-rates")
    public ResponseEntity<List> getRates() {
        return mixGifExchangeService.getRates();
    }
}