package com.example.myAirlineFlightservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirlineFlightservice.models.Airport;
import com.example.myAirlineFlightservice.repositories.AirportRepository;


@Service
public class AirportService {
    
    @Autowired
    private AirportRepository airportRepository;


    public Airport getByName(String name) {
        
        return airportRepository.findByName(name).orElseThrow(() ->
            new IllegalStateException("Could not find airport with name " + name + "."));
    }


    public Airport save(Airport airport) {

        return airportRepository.save(airport);
    }


    public void delete(Airport airport) {

        airportRepository.delete(airport);
    }
}