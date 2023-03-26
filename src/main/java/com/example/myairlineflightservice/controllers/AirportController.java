package com.example.myAirlineFlightservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirlineFlightservice.models.Airport;
import com.example.myAirlineFlightservice.services.AirportService;

import jakarta.validation.constraints.NotBlank;


@RestController
@RequestMapping("/airport")
public class AirportController {
    
    @Autowired
    private AirportService airportService;


    @GetMapping("/getByName")
    public Airport getByName(@RequestParam String name) {

        return airportService.getByName(name);
    }


    // TODO: only name instead of object??
    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airport saved.")
    public Airport save(@RequestBody Airport airport) {

        return airportService.save(airport);
    }

    
    // TODO: only name instead of object??
    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airport deleted.")
    public void delete(@RequestParam String name) {

        airportService.deleteByName(name);
    }


    @GetMapping("/exists")
    public boolean existsByName(@RequestParam String name) {

        return airportService.exists(name);
    }
}