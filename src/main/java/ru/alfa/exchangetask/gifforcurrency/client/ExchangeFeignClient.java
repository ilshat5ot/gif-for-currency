package ru.alfa.exchangetask.gifforcurrency.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.alfa.exchangetask.gifforcurrency.model.Exchange;

@FeignClient(name = "client-exchange.name", url = "client-exchange.post.baseUrl")
public interface ExchangeFeignClient {

    @GetMapping
    Exchange getCurrencyNow();

    @GetMapping
    Exchange getCurrencyHistorical();
}
