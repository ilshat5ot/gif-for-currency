package ru.alfa.exchangetask.gifforcurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GifForCurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GifForCurrencyApplication.class, args);
    }

}
