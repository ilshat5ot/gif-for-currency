package ru.alfa.exchangetask.gifforcurrency.service.exchange;

import ru.alfa.exchangetask.gifforcurrency.model.Exchange;

public interface ExchangeService {

    Exchange getCurrencyNow();

    Exchange getCurrencyHistorical();
}
