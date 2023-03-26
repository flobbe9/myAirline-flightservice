package com.example.myAirlineFlightservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirlineFlightservice.models.City;
import com.example.myAirlineFlightservice.repositories.CityRepository;


@Service
public class CityService {
    
    @Autowired
    private CityRepository cityRepository;


    public City getByName(String name) {
        
        return cityRepository.findByName(name).orElseThrow(() ->
            new IllegalStateException("Could not find city with name " + name + "."));
    }


    public City save(City city) {

        return cityRepository.save(city);
    }


    public void delete(City city) {

        cityRepository.delete(city);
    }
}