package com.miaoqi.security.controller;

import com.miaoqi.security.order.OrderInfo;
import com.miaoqi.security.order.PriceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public OrderInfo create(@RequestBody OrderInfo info) {
        PriceInfo price = this.restTemplate.getForObject("http://localhost:9060/prices/" + info.getProductId(), PriceInfo.class);
        log.info("price is " + price.getPrice());
        return info;
    }

}
