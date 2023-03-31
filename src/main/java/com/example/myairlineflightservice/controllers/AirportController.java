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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


/**
 * Class handling endpoints related to the {@link Airport} entity.
 * 
 * @since 0.0.1
 */
@RestController
@RequestMapping("/airport")
@Validated
public class AirportController {
    
    @Autowired
    private AirportService airportService;


    @GetMapping("/getByName")
    @Operation(summary = "Get airport by name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found airport.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Airport with this name not found.", content = {@Content(mediaType = "application/json")}),
    })
    public Airport getByName(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return airportService.getByName(name);
    }


    @GetMapping("/getById/{id}")
    @Operation(summary = "Get airport by id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found airport.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid id.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Airport with this id not found.", content = {@Content(mediaType = "application/json")}),
    })
    public Airport getById(@Min(value = 1, message = "Id must be greater than 0.") @PathVariable long id) {

        return airportService.getById(id);
    }


    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airport saved.")
    @Operation(summary = "Save given airport in db.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Saved airport.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid airport.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Airport does already exist.", content = {@Content(mediaType = "application/json")}),
    })
    public void save(@Valid @RequestBody Airport airport) {

        airportService.save(airport);
    }


    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airport and related flights updated.")
    @Operation(summary = "Replace existing airport in db with given airport (must have same id).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Airport updated.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid airport.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Airport does not exist or failed to update related flights.", content = {@Content(mediaType = "application/json")}),
    })
    public void update(@Valid @RequestBody Airport airport) {

        airportService.update(airport);
    }


    @GetMapping("/exists")
    @Operation(summary = "Returns true if at least one airport with given name exists.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "'True' if exists, else 'False'.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
    })
    public boolean exists(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return airportService.exists(name);
    }

    
    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airport deleted.")
    @Operation(summary = "Delete airport by name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Airport deleted.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Airport with this name does not exist or has related entites.", content = {@Content(mediaType = "application/json")}),
    })
    public void delete(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        airportService.delete(name);
    }


    @DeleteMapping("/deleteAllByCity")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airports deleted.")
    @Operation(summary = "Delete all airports related to given city.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Airports deleted.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid airport name.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "City does not exist or failed to update related flights.", content = {@Content(mediaType = "application/json")}),
    })
    public void deleteAllByAirport(@NotBlank(message = "Cannot leave parameter 'cityName' empty.") @RequestParam String cityName) {

        airportService.deleteAllByCityName(cityName);
    }
}