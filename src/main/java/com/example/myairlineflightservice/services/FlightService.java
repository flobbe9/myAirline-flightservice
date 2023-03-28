package com.example.myAirlineFlightservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirlineFlightservice.models.Flight;
import com.example.myAirlineFlightservice.repositories.FlightRepository;


@Service
public class FlightService {
    
    @Autowired
    private FlightRepository flightRepository;


    // public Flight getByName(String name) {
        
    //     return flightRepository.findByName(name).orElseThrow(() ->
    //         new IllegalStateException("Could not find flight: " + name + "."));
    // }


    public Flight getById(long id) {

        return flightRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find flight with id: " + id + "."));
    }


    // public Flight save(Flight flight) {

    //     String name = flight.getName();

    //     // should not already exist
    //     if (exists(name))
    //         throw new IllegalStateException("Failed to save aiport: " + name + ". Flight already exists.");

    //     return flightRepository.save(flight);
    // }


    // public boolean exists(String name) {

    //     return flightRepository.existsByName(name);
    // }


    // public void deleteByName(String name) {
      
    //     // should exist
    //     if (!exists(name))
    //         throw new IllegalStateException("Failed to delete aiport: " + name + ". Not found in db before deletion.");

    //     flightRepository.deleteByName(name);

    //     // should have been deleted
    //     if (exists(name))
    //         throw new IllegalStateException("Failed to delete flight: " + name + ". Still in db after deletion.");
    // }
}