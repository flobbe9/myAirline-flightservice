package com.example.myAirlineFlightservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirlineFlightservice.models.Airport;
import com.example.myAirlineFlightservice.services.AirportService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@RestController
@RequestMapping("/airport")
@Validated
public class AirportController {
    
    @Autowired
    private AirportService airportService;


    @GetMapping("/getByName")
    public Airport getByName(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return airportService.getByName(name);
    }


    @GetMapping("/getById/{id}") 
    public Airport getById(@Min(value = 1, message = "Id must be greater than 0.") @PathVariable long id) {

        return airportService.getById(id);
    }


    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airport saved.")
    public void save(@Valid @RequestBody Airport airport) {

        airportService.save(airport);
    }


    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airport and related flights updated.")
    public void update(@Valid @RequestBody Airport airport) {

        airportService.update(airport);
    }


    @GetMapping("/exists")
    public boolean exists(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return airportService.exists(name);
    }

    
    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airport deleted.")
    public void delete(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        airportService.delete(name);
    }


    @DeleteMapping("/deleteAllByCity")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airports deleted.")
    public void deleteAllByAirport(@NotBlank(message = "Cannot leave parameter 'cityName' empty.") @RequestParam String cityName) {

        airportService.deleteAllByCityName(cityName);
    }
}