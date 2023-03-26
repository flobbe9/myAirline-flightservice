package com.example.myAirlineFlightservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirlineFlightservice.models.City;
import com.example.myAirlineFlightservice.services.CityService;

import jakarta.validation.constraints.NotBlank;


@RestController
@RequestMapping("/city")
public class CityController {
    
    @Autowired
    private CityService cityService;


    @GetMapping("/getByName")
    public City getByName(@RequestParam @NotBlank String name) {

        return cityService.getByName(name);
    }


    // TODO: only name instead of object??
    @PostMapping("/save")
    public City save(@RequestBody City city) {

        return cityService.save(city);
    }

    
    // TODO: only name instead of object??
    @DeleteMapping("/delete")
    public void delete(@RequestBody City city) {

        cityService.delete(city);
    }
}
