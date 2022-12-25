package ru.alfa.exchangetask.gifforcurrency.service.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import ru.alfa.exchangetask.gifforcurrency.model.Exchange;
import ru.alfa.exchangetask.gifforcurrency.service.exchange.ExchangeServiceImpl;
import ru.alfa.exchangetask.gifforcurrency.service.gif.GifServiceImpl;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GifForCurrencyServiceImplTest {

    @InjectMocks
    private GifForCurrencyServiceImpl mixServer;
    @Mock
    private ExchangeServiceImpl exchangeService;
    @Mock
    private GifServiceImpl gifService;
    private final String brokenTag = "broken";
    private static Exchange currencyNow;
    private static Exchange currencyOneDayLater;


    @BeforeAll
    public static void getData() throws IOException {
        try(FileReader fileReader = new FileReader("src/test/resources/responseExchangeServer.json")){
            currencyNow = new ObjectMapper().readValue(fileReader, Exchange.class);
            currencyOneDayLater = new Exchange(currencyNow);

            Map<String, Double> map = currencyOneDayLater.getRates();
            for(String str : map.keySet()) {
                map.replace(str, map.get(str) * 2);
            }
        }
    }

    @Test
    public void testGetRates() {
        when(exchangeService.getCurrency(LocalDate.now().toString())).thenReturn(currencyNow);
        ResponseEntity<List> list = mixServer.getRates();

        verify(exchangeService, times(1)).getCurrency(anyString());
        Objects.requireNonNull(list.getBody()).contains("EUR");
    }

    @Test
    public void testGifForCurrency() {
        when(exchangeService.getCurrency(LocalDate.now().toString())).thenReturn(currencyNow);
        when(exchangeService.getCurrency(LocalDate.now().minusDays(1).toString())).thenReturn(currencyOneDayLater);
        when(gifService.getGif(brokenTag)).thenReturn("https://giphy.com/gifs/originals-pay-day-CDNfxKfNlrPFj2J9US");
        ReflectionTestUtils.setField(mixServer, "brokenTag", brokenTag);

        ResponseEntity<Map> responseServer = mixServer.getGifForCurrency("RUB");
        verify(exchangeService, times(2)).getCurrency(anyString());
        assertEquals(brokenTag, responseServer.getBody().get("category"));
    }
}