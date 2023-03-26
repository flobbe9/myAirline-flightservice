package com.example.myAirlineFlightservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Airport getByName(@RequestParam @NotBlank String name) {

        return airportService.getByName(name);
    }


    // TODO: only name instead of object??
    @PostMapping("/save")
    public Airport save(@RequestBody Airport airport) {

        return airportService.save(airport);
    }

    
    // TODO: only name instead of object??
    @DeleteMapping("/delete")
    public void delete(@RequestBody Airport airport) {

        airportService.delete(airport);
    }
}