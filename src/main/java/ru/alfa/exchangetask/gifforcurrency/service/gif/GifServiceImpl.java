package ru.alfa.exchangetask.gifforcurrency.service.gif;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfa.exchangetask.gifforcurrency.client.GifFeignClient;
import ru.alfa.exchangetask.gifforcurrency.model.Gif;

@Service
public class GifServiceImpl implements GifService {

    @Value("${client-gif.token}")
    private String token;
    private final GifFeignClient gifFeignClient;

    public GifServiceImpl(GifFeignClient gifFeignClient) {
        this.gifFeignClient = gifFeignClient;
    }

    @Override
    public Gif getGif(String tag) {
        return gifFeignClient.getGif(token, tag);
    }
}