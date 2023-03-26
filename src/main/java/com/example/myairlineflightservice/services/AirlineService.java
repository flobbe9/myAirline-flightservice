package com.example.myAirlineFlightservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirlineFlightservice.models.Airline;
import com.example.myAirlineFlightservice.repositories.AirlineRepository;


@Service
public class AirlineService {
    
    @Autowired
    private AirlineRepository airlineRepository;


    public Airline getByName(String name) {
        
        return airlineRepository.findByName(name).orElseThrow(() ->
            new IllegalStateException("Could not find airline: " + name + "."));
    }


    public Airline save(Airline airline) {

        return airlineRepository.save(airline);
    }


    public void delete(Airline airline) {

        airlineRepository.delete(airline);
    }
}