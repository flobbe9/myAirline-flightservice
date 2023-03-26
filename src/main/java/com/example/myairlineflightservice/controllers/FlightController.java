package com.example.myAirlineFlightservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirlineFlightservice.models.Flight;
import com.example.myAirlineFlightservice.services.FlightService;

import jakarta.validation.constraints.NotBlank;


@RestController
@RequestMapping("/flight")
public class FlightController {
    
    @Autowired
    private FlightService flightService;


    @GetMapping("/getById")
    public Flight getById(@RequestParam @NotBlank Long id) {

        return flightService.getById(id);
    }


    // TODO: only name instead of object??
    @PostMapping("/save")
    public Flight save(@RequestBody Flight flight) {

        return flightService.save(flight);
    }

    
    // TODO: only name instead of object??
    @DeleteMapping("/delete")
    public void delete(@RequestBody Flight flight) {

        flightService.delete(flight);
    }
}
