package com.example.myAirlineFlightservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirlineFlightservice.models.Flight;
import com.example.myAirlineFlightservice.repositories.FlightRepository;


@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;


    public Flight getById(Long id) {
        
        return flightRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find flight with name " + id + "."));
    }


    public Flight save(Flight flight) {

        return flightRepository.save(flight);
    }


    public void delete(Flight flight) {

        flightRepository.delete(flight);
    }
}