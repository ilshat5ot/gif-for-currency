package ru.alfa.exchangetask.gifforcurrency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfa.exchangetask.gifforcurrency.model.Exchange;
import ru.alfa.exchangetask.gifforcurrency.model.Gif;
import ru.alfa.exchangetask.gifforcurrency.service.mix.MixGifExchangeService;

@RestController
@RequestMapping("/currency")
public class GifForCurrency {

    private final MixGifExchangeService mixGifExchangeService;

    public GifForCurrency(MixGifExchangeService mixGifExchangeService) {
        this.mixGifExchangeService = mixGifExchangeService;
    }

    @GetMapping("/{currency}")
    public Gif get(@PathVariable String currency){
        return mixGifExchangeService.getGifForCurrency(currency);
    }
}