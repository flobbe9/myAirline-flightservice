package com.example.myAirlineFlightservice.controllers;

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


    // @GetMapping("/getByName")
    // public Flight getByName(@NotBlank @RequestParam String name) {

    //     return flightService.getByName(name);
    // }


    @GetMapping("/getById/{id}") 
    public Flight getById(@NotNull 
                           @Min(1) 
                           @PathVariable long id) {

        return flightService.getById(id);
    }


    // @PostMapping("/save")
    // @ResponseStatus(code = HttpStatus.OK, reason = "Flight saved.")
    // public void save(@Valid @RequestBody Flight flight) {

    //     flightService.save(flight);
    // }


    // @GetMapping("/exists")
    // public boolean existsByName(@NotBlank @RequestParam String name) {

    //     return flightService.exists(name);
    // }

    
    // @DeleteMapping("/delete")
    // @ResponseStatus(code = HttpStatus.OK, reason = "Flight deleted.")
    // public void delete(@NotBlank @RequestParam String name) {

    //     flightService.deleteByName(name);
    // }
}