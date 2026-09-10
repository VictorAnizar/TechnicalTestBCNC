package com.techtest.bcnc.prices.domain.exceptons;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(String message) {
        super(message);
    }
}
