package com.example.myAirlineFlightservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirlineFlightservice.models.Airport;
import com.example.myAirlineFlightservice.repositories.AirportRepository;

import jakarta.validation.constraints.NotBlank;


@Service
public class AirportService {
    
    @Autowired
    private AirportRepository airportRepository;


    public Airport getByName(String name) {
        
        return airportRepository.findByName(name).orElseThrow(() ->
            new IllegalStateException("Could not find airport: " + name + "."));
    }


    // TODO: works only at 7th time
    public Airport save(Airport airport) {

        System.out.println(airport.getId());

        String name = airport.getName();

        if (exists(name))
            throw new IllegalStateException("Failed to save aiport: " + name + ". Airport already exists.");

        return airportRepository.save(airport);
    }


    public boolean exists(String name) {

        return airportRepository.existsByName(name);
    }


    public void deleteByName(String name) {
      
        // should exist
        if (!exists(name))
            throw new IllegalStateException("Failed to delete aiport: " + name + ". Not found in db before deletion.");

        airportRepository.deleteByName(name);

        // should be deleted
        if (exists(name))
            throw new IllegalStateException("Failed to delete airport: " + name + ". Still in db after deletion.");
    }
}