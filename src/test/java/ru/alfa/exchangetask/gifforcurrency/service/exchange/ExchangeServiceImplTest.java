package ru.alfa.exchangetask.gifforcurrency.service.exchange;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alfa.exchangetask.gifforcurrency.client.ExchangeFeignClient;
import ru.alfa.exchangetask.gifforcurrency.model.Exchange;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceImplTest {

    @InjectMocks
    private ExchangeServiceImpl exchangeService;
    @Mock
    private ExchangeFeignClient feignClient;
    private static Exchange responseServer;


    @BeforeAll
    public void getData() throws IOException {

        try(FileReader fileReader = new FileReader("src/test/resources/responseExchangeServer.json")){
            responseServer = new ObjectMapper().readValue(fileReader, Exchange.class);
        }
    }

    @Test
    public void testResponseExchangeServer() {
        when(feignClient.getCurrency("1234", null)).thenReturn(responseServer);
        Exchange exchange = exchangeService.getCurrency("1234");
        verify(feignClient, times(1)).getCurrency("1234", null);
        assertEquals("USD", exchange.getBase());
    }
}