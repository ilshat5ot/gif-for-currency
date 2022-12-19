package ru.alfa.exchangetask.gifforcurrency.service.gif;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfa.exchangetask.gifforcurrency.client.GifFeignClient;

import java.util.Map;

@Service
public class GifServiceImpl implements GifService {

    @Value("${client-gif.token}")
    private String token;
    private final GifFeignClient gifFeignClient;

    public GifServiceImpl(GifFeignClient gifFeignClient) {
        this.gifFeignClient = gifFeignClient;
    }

    @Override
    public String getGif(String tag) {
        Map responseGif = gifFeignClient.getGif(token, tag);
        Map data = (Map) responseGif.get("data");
        Map images = (Map) data.get("images");
        Map fixedHeightDownSampled = (Map) images.get("fixed_height_downsampled");

        return fixedHeightDownSampled.get("url").toString();
    }
}