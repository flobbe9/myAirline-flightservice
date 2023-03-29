package com.example.myAirlineFlightservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.Airport;
import com.example.myAirlineFlightservice.repositories.AirportRepository;
import com.example.myAirlineFlightservice.repositories.FlightRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Service
@Validated
public class AirportService extends AbstractService<Airport> {

    private AirportRepository airportRepository;
    
    @Autowired
    private CityService cityService;

    @Autowired
    private FlightRepository flightRepository;

    
    public AirportService(AirportRepository repository) {
        super(repository, "airport");
        this.airportRepository = repository;
    }

    
    public Airport getById(@Min(1) long id) {

        return airportRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find airport with id: " + id + "."));
    }


    public Airport save(@Valid Airport airport) {

        String airportName = airport.getName();

        // airport should not exist
        if (exists(airportName))
            throw new IllegalStateException("Failed to save aiport: " + airportName + ". Airport already exists.");

        // city should exist
        cityService.getByName(airport.getCityName());

        return airportRepository.save(airport);
    }


    public Airport update(@Valid Airport airport) {

        Long id = airport.getId();

        // id should not be null
        if (id == null)
            throw new IllegalStateException("Failed to update airport: Id cannot be null.");

        // should exist
        getById(id);

        // update flights
        

        return airportRepository.save(airport);
    }


    @Override
    public void delete(@NotBlank String name) {

        // should exist
        exists(name);

        // no related flights should exist
        if (flightRepository.existsByDepartureAirportNameOrArrivalAirportName(name, name))
            throw new IllegalStateException("Failed to delete airport: " + name + ". Delete related entities first.");
      
        airportRepository.deleteByName(name);
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