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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * Class handling endpoints related to the {@link City} entity.
 * 
 * @since 0.0.1
 */
@RestController
@RequestMapping("/city")
@Validated
public class CityController {
    
    @Autowired
    private CityService cityService;


    @GetMapping("/getByName")
    @Operation(summary = "Get city by name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found city.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "City with this name not found.", content = {@Content(mediaType = "application/json")}),
    })
    public City getByName(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return cityService.getByName(name);
    }


    @GetMapping("/getById/{id}") 
    @Operation(summary = "Get city by id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found city.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid id.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "City with this id not found.", content = {@Content(mediaType = "application/json")}),
    })
    public City getById(@NotNull 
                        @Min(value = 1, message = "Id must be greater than 0.")
                        @PathVariable long id) {

        return cityService.getById(id);
    }


    @PostMapping("/save")
    @Operation(summary = "Save given city in db.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Saved city.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid city.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "City does already exist.", content = {@Content(mediaType = "application/json")}),
    })
    @ResponseStatus(code = HttpStatus.OK, reason = "City saved.")
    public void save(@Valid @RequestBody City city) {

        cityService.save(city);
    }


    @GetMapping("/exists")
    @Operation(summary = "Returns true if at least one city with given name exists.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "'True' if exists, else 'False'.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
    })
    public boolean exists(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return cityService.exists(name);
    }

    
    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK, reason = "City deleted.")
    @Operation(summary = "Delete city by name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "City deleted.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "City with this name does not exist or has related entites.", content = {@Content(mediaType = "application/json")}),
    })
    public void delete(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        cityService.delete(name);
    }


    @DeleteMapping("/deleteAllByCountry")
    @ResponseStatus(code = HttpStatus.OK, reason = "Cities deleted.")
    @Operation(summary = "Delete all citys related to given country.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Citys deleted.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid city name.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "City does not exist or failed to update related airports.", content = {@Content(mediaType = "application/json")}),
    })
    public void deleteAllByCountry(@NotBlank(message = "Cannot leave parameter 'countryName' empty.") @RequestParam String countryName) {

        cityService.deleteAllByCountryName(countryName);
    }
}