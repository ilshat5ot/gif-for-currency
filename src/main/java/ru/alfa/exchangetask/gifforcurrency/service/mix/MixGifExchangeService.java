package ru.alfa.exchangetask.gifforcurrency.service.mix;

import ru.alfa.exchangetask.gifforcurrency.model.Gif;

public interface MixGifExchangeService {

    Gif getGifForCurrency(String currency);
}
