package com.example.myAirlineFlightservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.Airline;
import com.example.myAirlineFlightservice.repositories.AirlineRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;


/**
 * Service class handling any logic related to {@link Airline}.
 * <p>
 * Extends {@link AbstractService}.
 * 
 * @since 0.0.1
 */
@Service
@Validated
public class AirlineService extends AbstractService<Airline> {
    
    @Autowired
    private AirlineRepository airlineRepository;
    
    
    public AirlineService(AirlineRepository repository) {
        super(repository, "airline");
    }


    /**
     * Find airline by id.
     * 
     * @param id of the airline
     * @return the airline with the given id
     * @throws IllegalStateException if not found.
     */
    public Airline getById(@Min(1) long id) {

        return airlineRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find airline with id: " + id + "."));
    }


    /**
     * Save given airline in db.
     * 
     * @param airline to save in db
     * @return saved airline
     * @throws IllegalStateException if already exists.
     */
    public Airline save(@Valid Airline airline) {

        String airlineName = airline.getName();

        // airport should not exist
        if (exists(airlineName))
            throw new IllegalStateException("Failed to save aiport: " + airlineName + ". Airline already exists.");

        return airlineRepository.save(airline);
    }
}