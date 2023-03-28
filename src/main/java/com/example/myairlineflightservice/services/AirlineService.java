package com.example.myAirlineFlightservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirlineFlightservice.models.Airline;
import com.example.myAirlineFlightservice.repositories.AirlineRepository;


@Service
public class AirlineService extends AbstractService<Airline> {
    
    @Autowired
    private AirlineRepository airlineRepository;
    
    
    public AirlineService(AirlineRepository repository) {
        super(repository, "airline");
        this.airlineRepository = repository;
    }
}