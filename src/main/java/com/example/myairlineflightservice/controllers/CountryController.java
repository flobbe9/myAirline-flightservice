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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get country by name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found country.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Country with this name not found.", content = {@Content(mediaType = "application/json")}),
    })
    public Country getByName(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return countryService.getByName(name);
    }


    @GetMapping("/getById/{id}") 
    @Operation(summary = "Get country by id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found country.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid id.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Country with this id not found.", content = {@Content(mediaType = "application/json")}),
    })
    public Country getById(@NotNull 
                           @Min(value = 1, message = "Id must be greater than 0.") 
                           @PathVariable long id) {

        return countryService.getById(id);
    }


    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.OK, reason = "Country saved.")
    @Operation(summary = "Save given country in db.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Saved country.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid country.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Country does already exist.", content = {@Content(mediaType = "application/json")}),
    })
    public void save(@Valid @RequestBody Country country) {

        countryService.save(country);
    }


    @GetMapping("/exists")
    @Operation(summary = "Returns true if at least one country with given name exists.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "'True' if exists, else 'False'.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
    })
    public boolean exists(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return countryService.exists(name);
    }

    
    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK, reason = "Country deleted.")
    @Operation(summary = "Delete airport by name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Airport deleted.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Country with this name does not exist or has related entites.", content = {@Content(mediaType = "application/json")})
    })
    public void delete(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        countryService.delete(name);
    }
}