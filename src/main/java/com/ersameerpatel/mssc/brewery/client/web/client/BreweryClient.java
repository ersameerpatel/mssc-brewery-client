package com.ersameerpatel.mssc.brewery.client.web.client;

import com.ersameerpatel.mssc.brewery.client.web.model.BeerDto;
import com.ersameerpatel.mssc.brewery.client.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@ConfigurationProperties(prefix = "mssc.brewery", ignoreUnknownFields = false)
@Component
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer/";
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

    private String apihost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid){
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }

    public ResponseEntity saveNewBeer(BeerDto beerDto){
        //return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
        return restTemplate.postForEntity(apihost + BEER_PATH_V1, beerDto, ResponseEntity.class );
    }

    public void updateBeer(UUID uuid, BeerDto beerDto){
        restTemplate.put(apihost + BEER_PATH_V1 + uuid.toString(), beerDto);
    }

    public void deleteBeer(UUID beerId){
        restTemplate.delete(apihost + BEER_PATH_V1 + beerId.toString());
    }

    public CustomerDto getCustomerById(UUID customerId){
        return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + customerId.toString(), CustomerDto.class );
    }

    public URI saveNewCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
    }

    public void updateCustomer(UUID customerId, CustomerDto customerDto){
        restTemplate.put(apihost + CUSTOMER_PATH_V1 + customerId, customerDto);
    }

    public void deleteCustomer(UUID customerId){
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + customerId);
    }

    public void setApiHost(String apihost) {
        this.apihost = apihost;
    }
}




