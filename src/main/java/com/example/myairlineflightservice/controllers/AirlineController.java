package com.example.myAirlineFlightservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirlineFlightservice.models.Airline;
import com.example.myAirlineFlightservice.services.AirlineService;

import jakarta.validation.constraints.NotBlank;


@RestController
@RequestMapping("/airline")
public class AirlineController {
    
    @Autowired
    private AirlineService airlineService;


    @GetMapping("/getByName")
    public Airline getByName(@RequestParam @NotBlank String name) {

        return airlineService.getByName(name);
    }


    // TODO: only name instead of object??
    @PostMapping("/save")
    public Airline save(@RequestBody Airline airline) {

        return airlineService.save(airline);
    }

    
    // TODO: only name instead of object??
    @DeleteMapping("/delete")
    public void delete(@RequestBody Airline airline) {

        airlineService.delete(airline);
    }
}