package com.example.myAirlineFlightservice.utils;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 * Helper class with some http request sending methods.
 * 
 * @since 0.0.1
 */
public class HttpRequestSender {

    public static String BASE_URL = "http://localhost:4001";
    
    /**
     * Sends a simple http request accepting application/json as content-type. 
     * <p>
     * Supports any request method but no request body can be used at the moment.
     * 
     * @param url to send the request to.
     * @param method request method to use.
     * @return the response as Object.
     */
    public static Object sendSimpleRequest(String url, HttpMethod method) {

        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ResponseEntity<?> response = new RestTemplate().exchange(url,   
                                                                 method, 
                                                                 new HttpEntity<>(headers), 
                                                                 Object.class);

        return response.getBody();
    }
}