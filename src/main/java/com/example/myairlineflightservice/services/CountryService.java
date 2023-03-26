package com.example.myAirlineFlightservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirlineFlightservice.models.Country;
import com.example.myAirlineFlightservice.repositories.CountryRepository;


@Service
public class CountryService {
    
    @Autowired
    private CountryRepository countryRepository;


    public Country getByName(String name) {
        
        return countryRepository.findByName(name).orElseThrow(() ->
            new IllegalStateException("Could not find country: " + name + "."));
    }


    public Country save(Country country) {

        return countryRepository.save(country);
    }


    public void delete(Country country) {

        countryRepository.delete(country);
    }
}