package com.ersameerpatel.mssc.brewery.client.web.client;

import com.ersameerpatel.mssc.brewery.client.web.model.BeerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerById() {

        BeerDto dto = client.getBeerById(UUID.randomUUID());
        Assertions.assertNotNull(dto);
        System.out.println("JUnit:: Received BeerDto:: " + dto.toString());
    }

    @Test
    void saveNewBeer(){

        URI uri = client.saveNewBeer(BeerDto.builder().beerName("NewBeer").build());
        Assertions.assertNotNull(uri);
        System.out.println("JUnit :: URI Received :: " + uri);
    }
}