package com.example.myAirlineFlightservice.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.Airport;
import com.example.myAirlineFlightservice.repositories.AirportRepository;
import com.example.myAirlineFlightservice.repositories.FlightRepository;
import com.example.myAirlineFlightservice.utils.HttpRequestSender;

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

    private static final String BASE_URL_FLIGHT_SERVICE = "http://localhost:4001";

    
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


    public void update(@Valid Airport newAirport) {

        Long id = newAirport.getId();

        // id should not be null
        if (id == null)
            throw new IllegalStateException("Failed to update airport: Id cannot be null.");

        // should exist
        String oldName = getById(id).getName();
        String newName = newAirport.getName();

        // update related flights
        String url = BASE_URL_FLIGHT_SERVICE + "/flight/updateAllByAirportName?oldAirportName=" + oldName + "&newAirportName=" + newName;
        HttpRequestSender.sendSimpleRequest(url, HttpMethod.PUT);
        
        airportRepository.save(newAirport);
    }


    @Override
    public void delete(@NotBlank String name) {

        // should exist
        getByName(name);

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

        // find related flights
        for (Airport airport : airports) {
            String airportName = airport.getName();

            if (flightRepository.existsByDepartureAirportNameOrArrivalAirportName(airportName, airportName))
                return true;
        }

        return false;
    }
}