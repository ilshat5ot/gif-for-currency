package ru.alfa.exchangetask.gifforcurrency.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "${client-gif.name}", url = "${client-gif.post.baseUrl}")
public interface GifFeignClient {

    @GetMapping
    Map getGif(@RequestParam String api_key, @RequestParam String tag);
}
