package ru.alfa.exchangetask.gifforcurrency.service.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.alfa.exchangetask.gifforcurrency.model.Exchange;
import ru.alfa.exchangetask.gifforcurrency.service.exchange.ExchangeService;
import ru.alfa.exchangetask.gifforcurrency.service.gif.GifService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map> getGifForCurrency(String currency) {
        if(compareCurrency(currency)) {
            return ResponseEntity.ok(Map.of("url", gifService.getGif(richTag),
                    "category", richTag));
        } else return ResponseEntity.ok(Map.of("url", gifService.getGif(brokenTag),
                "category", brokenTag));
    }

    @Override
    public ResponseEntity<List> getRates() {
        return ResponseEntity.ok(getRatesList());
    }

    private List<String> getRatesList() {
        Exchange exchange = exchangeService.getCurrency(LocalDate.now().toString());

        List<String> listRates = new ArrayList<>();
        listRates.addAll(exchange.getRates().keySet());

        return listRates;
    }

    private boolean compareCurrency(String currency) {
        Exchange exchangeNow = exchangeService.getCurrency(LocalDate.now().toString());
        Exchange exchangeHistorical = exchangeService.getCurrency(LocalDate.now().minusDays(1).toString());

        Double nowCurrency = exchangeNow.getRates().get(currency);
        Double historicalCurrency = exchangeHistorical.getRates().get(currency);
        return nowCurrency > historicalCurrency;
    }
}
