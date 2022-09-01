package ru.alfa.exchangetask.gifforcurrency.service.mix;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfa.exchangetask.gifforcurrency.model.Exchange;
import ru.alfa.exchangetask.gifforcurrency.model.Gif;
import ru.alfa.exchangetask.gifforcurrency.service.exchange.ExchangeService;
import ru.alfa.exchangetask.gifforcurrency.service.gif.GifService;

import java.time.LocalDate;

@Service
public class MixGifExchangeServiceImpl implements MixGifExchangeService {

    @Value("${client-gif.category-rich}")
    private String richTag;
    @Value("${client-gif.category-broken}")
    private String brokenTag;
    private final ExchangeService exchangeService;
    private final GifService gifService;

    public MixGifExchangeServiceImpl(ExchangeService exchangeService, GifService gifService) {
        this.exchangeService = exchangeService;
        this.gifService = gifService;
    }

    @Override
    public Gif getGifForCurrency(String currency) {
        return compareCurrency(currency) ? gifService.getGif(richTag) : gifService.getGif(brokenTag);
    }

    private boolean compareCurrency(String currency) {
        Exchange exchangeNow = exchangeService.getCurrencyNow();
        Exchange exchangeHistorical = exchangeService.getCurrencyHistorical(LocalDate.now().minusDays(1).toString());

        Double nowCurrency = exchangeNow.getRates().get(currency);
        Double historicalCurrency = exchangeHistorical.getRates().get(currency);

        return nowCurrency > historicalCurrency;
    }
}
