package com.techtest.bcnc.infraestructure.in.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private static final String ENDPOINT = "/bcnc/api/v1/prices";
    private static final String PRODUCT_ID = "35455";
    private static final String BRAND_ID = "1";

    //-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test1() throws Exception {
        mockMvc.perform(
                get(ENDPOINT)
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    //-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test2() throws Exception {
        mockMvc.perform(
                get(ENDPOINT)
                        .param("date", "2020-06-14T16:00:00")
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    //-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test3() throws Exception {
        mockMvc.perform(
                get(ENDPOINT)
                        .param("date", "2020-06-14T21:00:00")
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    //-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test4() throws Exception {
        mockMvc.perform(get(
                ENDPOINT)
                        .param("date", "2020-06-15T10:00:00")
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    //-   Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test5() throws Exception {
        mockMvc.perform(
                get(ENDPOINT)
                        .param("date", "2020-06-16T21:00:00")
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                  .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95));
    }
}