package com.ersameerpatel.mssc.brewery.client.web.client;

import com.ersameerpatel.mssc.brewery.client.web.model.BeerDto;
import com.ersameerpatel.mssc.brewery.client.web.model.CustomerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Locale;
import java.util.UUID;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void testGetBeerById() {

        BeerDto dto = client.getBeerById(UUID.randomUUID());
        Assertions.assertNotNull(dto);
        System.out.println("JUnit:: Received BeerDto:: " + dto.toString());
    }

    @Test
    void testSaveNewBeerPositiveTestCase(){

       ResponseEntity responseEntity = client.saveNewBeer(BeerDto.builder().beerName("NewBeer").build());
        //URI uri = client.saveNewBeer(BeerDto.builder().beerName("NewBeer").build());
        URI uri = responseEntity.getHeaders().getLocation();
        Assertions.assertNotNull(uri);
        System.out.println("JUnit :: URI Received :: " + uri);
    }

//    @Test
//    void testSaveNewBeerNegativeTestCase(){
//
//        ResponseEntity responseEntity = client.saveNewBeer(BeerDto.builder().beerName("NewBeer").id(UUID.randomUUID()).build());
//        //URI uri = client.saveNewBeer(BeerDto.builder().beerName("NewBeer").build());
//        URI uri = responseEntity.getHeaders().getLocation();
//        Assertions.assertNotNull(uri);
//        System.out.println("JUnit :: URI Received :: " + uri);
//    }

    @Test
    void testUpdateBeer(){
        client.updateBeer(UUID.randomUUID(), BeerDto.builder().beerName("NewBeer").build());
    }

    @Test
    void testDelete() {
        client.deleteBeer(UUID.randomUUID());
    }

    @Test
    void testSaveNewCustomer() {
        client.saveNewCustomer(CustomerDto.builder().build());
    }

    @Test
    void testUpdateCustomer() {
        client.updateCustomer(UUID.randomUUID(), CustomerDto.builder().build());
    }

    @Test
    void testDeleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}