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

import com.example.myAirlineFlightservice.models.Country;
import com.example.myAirlineFlightservice.services.CountryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@RestController
@RequestMapping("/country")
@Validated
public class CountryController {
    
    @Autowired
    private CountryService countryService;


    @GetMapping("/getByName")
    public Country getByName(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return countryService.getByName(name);
    }


    @GetMapping("/getById/{id}") 
    public Country getById(@NotNull 
                           @Min(value = 1, message = "Id must be greater than 0.") 
                           @PathVariable long id) {

        return countryService.getById(id);
    }


    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.OK, reason = "Country saved.")
    public void save(@Valid @RequestBody Country country) {

        countryService.save(country);
    }


    @GetMapping("/exists")
    public boolean existsByName(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return countryService.exists(name);
    }

    
    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK, reason = "Country deleted.")
    public void delete(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        countryService.delete(name);
    }
}