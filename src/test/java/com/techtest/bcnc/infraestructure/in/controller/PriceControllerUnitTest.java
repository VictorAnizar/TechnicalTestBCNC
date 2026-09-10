package com.techtest.bcnc.infraestructure.in.controller;

import com.techtest.bcnc.prices.domain.port.in.GetPriceUseCase;
import com.techtest.bcnc.prices.domain.models.Price;
import com.techtest.bcnc.prices.infraestructure.in.controllers.PriceController;
import com.techtest.bcnc.prices.infraestructure.in.dto.PriceResponse;
import com.techtest.bcnc.prices.infraestructure.in.mapper.PriceMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * ESTA ES UNA PRUEBA UNITARIA!!!!!
 *
 * Esta clase fue hecha sin un motor como MockMVC, la hice para testear la clase del controlador PriceController
 * No toma en cuenta la parte de spring, es decir, sus beans
 * Esta forma de testeo no valida si la consulta a la BD funciona, solo se valida si el controlador responde de foma esperada
 * Por eso se lama prueba unitaria
 */

@ExtendWith(MockitoExtension.class)
public class PriceControllerUnitTest {
    @InjectMocks
    private PriceController priceController;

    @Mock
    private GetPriceUseCase getPriceUseCase;

    @Mock
    private PriceMapper mapper;

    private Price mockPrice;
    private PriceResponse mockPriceResponse;

    @BeforeEach
    void setUp() {
        mockPrice = Price.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(1)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(BigDecimal.valueOf(35.50))
                .build();

        mockPriceResponse = PriceResponse.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(1)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(BigDecimal.valueOf(35.50))
                .build();
    }

    @Test
    void getPriceTest() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        when(getPriceUseCase.getApplicablePrice(any(LocalDateTime.class), any(Long.class), any(Long.class)))
                .thenReturn(mockPrice);
        when(mapper.toDto(any(Price.class)))
                .thenReturn(mockPriceResponse);

        ResponseEntity<PriceResponse> response = priceController.getPrice(date, productId, brandId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(BigDecimal.valueOf(35.50), response.getBody().getPrice());
        Assertions.assertEquals(1, response.getBody().getPriceList());

        verify(getPriceUseCase).getApplicablePrice(date, productId, brandId);
        verify(mapper).toDto(mockPrice);
    }
}
