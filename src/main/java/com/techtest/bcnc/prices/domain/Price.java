package com.techtest.bcnc.prices.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class Price{
    private Long brandId;
    private Date startDate;
    private Date endDate;
    private Integer priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
}
