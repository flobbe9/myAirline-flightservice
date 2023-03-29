package com.example.myAirlineFlightservice.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirlineFlightservice.models.Flight;
import com.example.myAirlineFlightservice.services.FlightService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@RestController
@RequestMapping("/flight")
@Validated
public class FlightController {
    
    @Autowired
    private FlightService flightService;


    @GetMapping("/getById/{id}") 
    public Flight getById(@NotNull 
                          @Min(value = 1, message = "Id must be greater than 0.")
                          @PathVariable long id) {

        return flightService.getById(id);
    }


    @GetMapping("/getByNumber")
    public Flight getByNumber(@NotNull 
                              @Min(value = 0, message = "Number cannot be negative.")
                              @RequestParam long number) {

        return flightService.getByNumber(number);
    }


    @GetMapping("/getAllByDepartureTime")
    public List<Flight> getAllByDepartureTime(@NotNull(message = "Departure time cannot be null.") 
                                              @RequestParam LocalTime departureTime) {

        return flightService.getAllByDepartureTimeAfter(departureTime);
    }


    @GetMapping("/getAllByDepartureDate")
    public List<Flight> getAllByDepartureDate(@NotNull(message = "Departure date cannot be null.")
                                             @RequestParam LocalDate departureDate) {

        return flightService.getAllByDepartureDate(departureDate);
    }


    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.OK, reason = "Flight saved.")
    public void save(@Valid @RequestBody Flight flight) {

        flightService.save(flight);
    }


    @GetMapping("/exists")
    public boolean exists(@Min(value = 0, message = "Number cannot be negative.") @RequestParam long number) {

        return flightService.exists(number);
    }


    @GetMapping("/existsByAirport")
    public boolean existsByAirport(@NotBlank(message = "Airport name cannot be blank.") @RequestParam String airportName) {

        return flightService.existsByAirport(airportName);
    }

    
    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK, reason = "Flight deleted.")
    public void delete(@Min(value = 0, message = "Number must not be negative.") @RequestParam long number) {

        flightService.delete(number);
    }


    @DeleteMapping("/deleteAllByAirport")
    @ResponseStatus(code = HttpStatus.OK, reason = "Flight deleted.")
    public void deleteAllByAirport(@NotBlank(message = "Airport name cannot be blank.") @RequestParam String airportName) {
        
        flightService.deleteAllByAirportName(airportName);
    }
}