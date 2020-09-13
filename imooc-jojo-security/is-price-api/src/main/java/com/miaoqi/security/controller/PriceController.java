package com.miaoqi.security.controller;

import com.miaoqi.security.price.PriceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/prices")
@Slf4j
public class PriceController {

    @GetMapping("/{id}")
    private PriceInfo getPrice(@PathVariable Long id) {
        PriceInfo priceInfo = new PriceInfo();
        priceInfo.setId(id);
        priceInfo.setPrice(new BigDecimal("100"));
        log.info("productId is " + id);
        return priceInfo;
    }

}
