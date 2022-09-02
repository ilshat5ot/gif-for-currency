package ru.alfa.exchangetask.gifforcurrency.service.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfa.exchangetask.gifforcurrency.model.Exchange;
import ru.alfa.exchangetask.gifforcurrency.model.Gif;
import ru.alfa.exchangetask.gifforcurrency.service.exchange.ExchangeService;
import ru.alfa.exchangetask.gifforcurrency.service.gif.GifService;

import java.time.LocalDate;

@Service
public class GifForCurrencyServiceImpl implements GifForCurrencyService {

    @Value("${client-gif.category-rich}")
    private String richTag;
    @Value("${client-gif.category-broken}")
    private String brokenTag;
    private final ExchangeService exchangeService;
    private final GifService gifService;

    public GifForCurrencyServiceImpl(ExchangeService exchangeService, GifService gifService) {
        this.exchangeService = exchangeService;
        this.gifService = gifService;
    }

    @Override
    public Gif getGifForCurrency(String currency) {
        return compareCurrency(currency) ? gifService.getGif(richTag) : gifService.getGif(brokenTag);
    }

    private boolean compareCurrency(String currency) {
        Exchange exchangeNow = exchangeService.getCurrency(LocalDate.now().toString());
        Exchange exchangeHistorical = exchangeService.getCurrency(LocalDate.now().minusDays(1).toString());

        Double nowCurrency = exchangeNow.getRates().get(currency);
        Double historicalCurrency = exchangeHistorical.getRates().get(currency);
        System.out.println("now " + nowCurrency);
        System.out.println("historical " + historicalCurrency);

        return nowCurrency > historicalCurrency;
    }
}
