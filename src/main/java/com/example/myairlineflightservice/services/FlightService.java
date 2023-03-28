package com.example.myAirlineFlightservice.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.Flight;
import com.example.myAirlineFlightservice.repositories.FlightRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@Service
@Validated
public class FlightService {
    
    @Autowired
    private FlightRepository flightRepository;


    public Flight getById(@Min(0) long id) {

        return flightRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find flight with id: " + id + "."));
    }


    public Flight getByNumber(@Min(0) long number) {

        return flightRepository.findByNumber(number).orElseThrow(() -> 
            new IllegalStateException("Could not find flight: " + number + "."));
    }


    public List<Flight> getAllByDepartureTimeAfter(@NotNull LocalTime departureTime) {

        return flightRepository.findAllByDepartureTimeAfter(departureTime);
    }


    public List<Flight> getAllByDepartureDate(@NotNull LocalDate departureDate) {

        // should not be in past
        if (!isDateValid(departureDate)) 
            throw new IllegalStateException("Failed to find flights: Date " + departureDate + " is invalid.");

        return flightRepository.findAllByDepartureDate(departureDate);
    }


    public Flight save(@Valid Flight flight) {

        // TODO: check that fields exist in db already

        long number = flight.getNumber();

        // should not exist
        if (exists(number))
            throw new IllegalStateException("Failed to save aiport: " + number + ". Flight already exists.");

        return flightRepository.save(flight);
    }


    public boolean exists(@Min(0) long number) {

        return flightRepository.existsByNumber(number);
    }


    public void delete(@Min(0) long number) {
      
        // should exist
        if (!exists(number))
            throw new IllegalStateException("Failed to delete aiport: " + number + ". Not found in db before deletion.");

        flightRepository.deleteByNumber(number);
    }


    /**
     * Checks that given date is not in the past.
     * @param departureDate date to check.
     * @return true if date is today or in the future.
     */
    private boolean isDateValid(@NotNull LocalDate departureDate) {

        return departureDate.equals(departureDate) && departureDate.isAfter(LocalDate.now());
    }
}