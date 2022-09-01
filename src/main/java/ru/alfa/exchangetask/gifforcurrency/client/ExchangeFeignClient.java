package ru.alfa.exchangetask.gifforcurrency.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfa.exchangetask.gifforcurrency.model.Exchange;

@FeignClient(name = "${client-exchange.name}", url = "${client-exchange.post.baseUrl}")
public interface ExchangeFeignClient {

    @GetMapping("/latest.json")
    Exchange getCurrencyNow(@RequestParam String app_id);

    @GetMapping("/historical/{date}.json")
    Exchange getCurrencyHistorical (@PathVariable String date, @RequestParam String app_id);
}
