package com.example.myAirlineFlightservice.testStuff;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public class TestRepository {
    
    private static final List<String> emails = new LinkedList<>(List.of("florin735@live.com"));


    public String save(String email) {

        emails.add(email);

        return email;
    }


    public boolean exists(String email) {

        return emails.contains(email);
    }


    public void deleteAll() {
        
        emails.clear();
    }
}