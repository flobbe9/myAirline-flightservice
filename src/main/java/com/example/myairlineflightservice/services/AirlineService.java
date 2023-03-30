package com.example.myAirlineFlightservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirlineFlightservice.models.Airline;
import com.example.myAirlineFlightservice.repositories.AirlineRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;


@Service
public class AirlineService extends AbstractService<Airline> {
    
    @Autowired
    private AirlineRepository airlineRepository;
    
    
    public AirlineService(AirlineRepository repository) {
        
        super(repository, "airline");
        this.airlineRepository = repository;
    }


    public Airline getById(@Min(1) long id) {

        return airlineRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find airline with id: " + id + "."));
    }


    public Airline save(@Valid Airline airline) {

        String airportName = airline.getName();

        // airport should not exist
        if (exists(airportName))
            throw new IllegalStateException("Failed to save aiport: " + airportName + ". Airline already exists.");

        return airlineRepository.save(airline);
    }
}