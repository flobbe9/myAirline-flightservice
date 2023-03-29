package com.example.myAirlineFlightservice.utils;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class HttpRequestSender {
    
    // TODO: try this with params and body
    // TODO: make this more generic
    public static Object sendSimpleRequest(String url, HttpMethod method) {

        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<?> response = new RestTemplate().exchange(url, method, entity, Object.class);

        return response.getBody();
    }
}