package com.example.myAirlineFlightservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.Airport;
import com.example.myAirlineFlightservice.repositories.AirportRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


@Service
@Validated
public class AirportService extends AbstractService<Airport> {

    private AirportRepository airportRepository;
    
    @Autowired
    private CityService cityService;

    
    public AirportService(AirportRepository repository) {
        super(repository, "airport");
        this.airportRepository = repository;
    }


    @Override
    public Airport save(@Valid Airport airport) {

        String airportName = airport.getName();

        // airport should not exist
        if (exists(airportName))
            throw new IllegalStateException("Failed to save aiport: " + airportName + ". Airport already exists.");

        // city should exist
        cityService.getByName(airport.getCityName());

        return airportRepository.save(airport);
    }


    public void deleteAllByCityName(@NotBlank String cityName) {

        // city should exist
        cityService.getByName(cityName);

        List<Airport> airports = airportRepository.findAllByCityName(cityName);

        // should'nt have related entites
        if (hasRelatedEntites(airports))
            throw new IllegalStateException("Failed to delete airports in city: " + cityName + ". Delete related entities first.");

        airportRepository.deleteAllByCityName(cityName);
    }


    private boolean hasRelatedEntites(List<Airport> airports) {

        // TODO: implement if neccessary

        return false;
    }
}