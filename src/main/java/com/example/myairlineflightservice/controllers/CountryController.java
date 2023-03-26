package com.example.myAirlineFlightservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirlineFlightservice.models.Country;
import com.example.myAirlineFlightservice.services.CountryService;

import jakarta.validation.constraints.NotBlank;


@RestController
@RequestMapping("/country")
public class CountryController {
    
    @Autowired
    private CountryService countryService;


    @GetMapping("/getByName")
    public Country getByName(@RequestParam @NotBlank String name) {

        return countryService.getByName(name);
    }


    // TODO: only name instead of object??
    @PostMapping("/save")
    public Country save(@RequestBody Country country) {

        return countryService.save(country);
    }

    
    // TODO: only name instead of object??
    @DeleteMapping("/delete")
    public void delete(@RequestBody Country country) {

        countryService.delete(country);
    }
}
