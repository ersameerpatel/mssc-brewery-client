package com.ersameerpatel.mssc.brewery.client.web.client;

import com.ersameerpatel.mssc.brewery.client.web.model.BeerDto;
import com.ersameerpatel.mssc.brewery.client.web.model.CustomerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.UUID;

@SpringBootTest
class TimeoutControllerTest {

    @Test
    void testConnectionTimeout() throws MalformedURLException {
        URL url = new URL("http://localhost:8080/api/v1/timeout/ISIN_VALUE");
        URLConnection connection = null;
        long startTime = System.currentTimeMillis();
        try {
            connection = url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.connect();
            //try(InputStream inputStream = url.openStream();
            try(InputStream inputStream = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
                String result = br.readLine().toLowerCase();
                System.out.println("Result::" + result);
            }
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;
            System.out.println("TimeTaken::" + timeTaken);
        } catch (SocketTimeoutException e) {
            System.out.print("SocketTimeoutException::" + e.getMessage());
        } catch (IOException e) {
            System.out.print("IOException::" + e.getMessage());
        } catch (Exception e) {
            System.out.print("Exception::" + e.getMessage());
        }

    }
}