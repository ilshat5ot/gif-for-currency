package ru.alfa.exchangetask.gifforcurrency.service.gif;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alfa.exchangetask.gifforcurrency.client.GifFeignClient;


import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GifServiceImplTest {

    @InjectMocks
    private GifServiceImpl gifService;
    @Mock
    private GifFeignClient gifFeignClient;
    private static Map responseGifServer;
    private static String EXPECTED_URL = "https://media2.giphy.com/media/CDNfxKfNlrPFj2J9US/" +
            "200_d.gif?cid=9dda02400ce79a5453747eaa861b248f6180ff98b8750a14&rid=200_d.gif&ct=g";

    @BeforeAll
    public static void getData() throws IOException {
        try(FileReader fileReader = new FileReader("src/test/resources/responseGifServer.json")){
            responseGifServer = new ObjectMapper().readValue(fileReader, HashMap.class);
        }
    }

    @Test
    public void testResponseForGifServer() {
        when(gifFeignClient.getGif(null, "rich")).thenReturn(responseGifServer);
        String url = gifService.getGif("rich");

        verify(gifFeignClient,times(1)).getGif(null, "rich");
        assertEquals(EXPECTED_URL, url);
    }
}