package ru.alfa.exchangetask.gifforcurrency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfa.exchangetask.gifforcurrency.model.Gif;

@RestController
public class GifForCurrency {

    @GetMapping
    public Gif get(){
        return null;
    }
}
