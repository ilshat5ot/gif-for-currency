package ru.alfa.exchangetask.gifforcurrency.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.alfa.exchangetask.gifforcurrency.model.Gif;

@FeignClient(name = "${client-gif.name}", url = "${client-gif.post.baseUrl}")
public interface GifFeignClient {

    @GetMapping
    Gif getGif();
}
