package com.techtest.bcnc.application.services;

import com.techtest.bcnc.prices.application.services.PriceService;
import com.techtest.bcnc.prices.domain.exceptons.PriceNotFoundException;
import com.techtest.bcnc.prices.domain.models.Price;
import com.techtest.bcnc.prices.domain.port.out.PriceRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceServiceUnitTest {
    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceService priceService;

    @Test
    void getApplicablePriceTest() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        Price highPriorityPrice = Price.builder().priority(1).price(BigDecimal.valueOf((25.45))).build();

        when(priceRepositoryPort.findPrice(date, 35455L, 1L))
                .thenReturn(Optional.of( highPriorityPrice));

        Price result = priceService.getApplicablePrice(date, 35455L, 1L);

        assertThat(result.getPrice()).isEqualTo(BigDecimal.valueOf(25.45));
        verify(priceRepositoryPort, times(1)).findPrice(date, 35455L, 1L);
    }

    @Test
    void getApplicablePriceTestWhenNoExist() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        when(priceRepositoryPort.findPrice(date, 35455L, 1L))
                .thenThrow(PriceNotFoundException.class);

        assertThrows(PriceNotFoundException.class,
                () -> priceService.getApplicablePrice(date, 35455L, 1L));
    }
}

