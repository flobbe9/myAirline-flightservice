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

import com.example.myAirlineFlightservice.models.City;
import com.example.myAirlineFlightservice.services.CityService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@RestController
@RequestMapping("/city")
@Validated
public class CityController {
    
    @Autowired
    private CityService cityService;


    @GetMapping("/getByName")
    public City getByName(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return cityService.getByName(name);
    }


    @GetMapping("/getById/{id}") 
    public City getById(@NotNull 
                        @Min(value = 1, message = "Id must be greater than 0.")
                        @PathVariable long id) {

        return cityService.getById(id);
    }


    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.OK, reason = "City saved.")
    public void save(@Valid @RequestBody City city) {

        cityService.save(city);
    }


    @GetMapping("/exists")
    public boolean exists(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return cityService.exists(name);
    }

    
    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK, reason = "City deleted.")
    public void delete(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        cityService.delete(name);
    }


    @DeleteMapping("/deleteAllByCountry")
    @ResponseStatus(code = HttpStatus.OK, reason = "Cities deleted.")
    public void deleteAllByCountry(@NotBlank(message = "Cannot leave parameter 'countryName' empty.") @RequestParam String countryName) {

        cityService.deleteAllByCountryName(countryName);
    }
}