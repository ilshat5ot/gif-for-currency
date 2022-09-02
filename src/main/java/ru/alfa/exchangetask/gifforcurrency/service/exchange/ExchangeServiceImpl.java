package ru.alfa.exchangetask.gifforcurrency.service.exchange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfa.exchangetask.gifforcurrency.client.ExchangeFeignClient;
import ru.alfa.exchangetask.gifforcurrency.model.Exchange;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Value("${client-exchange.token}")
    private String token;
    private final ExchangeFeignClient exchangeFeignClient;

    public ExchangeServiceImpl(ExchangeFeignClient exchangeFeignClient) {
        this.exchangeFeignClient = exchangeFeignClient;
    }

    @Override
    public Exchange getCurrency(String date) {
        return exchangeFeignClient.getCurrency(date, token);
    }
}
