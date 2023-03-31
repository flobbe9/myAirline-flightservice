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

import com.example.myAirlineFlightservice.models.Airline;
import com.example.myAirlineFlightservice.services.AirlineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * Class handling endpoints related to the {@link Airline} entity.
 * 
 * @since 0.0.1
 */
@RestController
@RequestMapping("/airline")
@Validated
public class AirlineController {
    
    @Autowired
    private AirlineService airlineService;


    @GetMapping("/getByName")
    @Operation(summary = "Get airline by name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found airline.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Airline with this name not found.", content = {@Content(mediaType = "application/json")}),
    })
    public Airline getByName(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return airlineService.getByName(name);
    }


    @GetMapping("/getById/{id}") 
    @Operation(summary = "Get airline by id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found airline.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid id.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Airline with this id not found.", content = {@Content(mediaType = "application/json")}),
    })
    public Airline getById(@NotNull 
                           @Min(value = 1, message = "Id must be greater than 0.")
                           @PathVariable long id) {

        return airlineService.getById(id);
    }


    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airline saved.")
    @Operation(summary = "Save given airline in db.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Saved airline.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid airline.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Airline does already exist.", content = {@Content(mediaType = "application/json")}),
    })
    public void save(@Valid @RequestBody Airline airline) {

        airlineService.save(airline);
    }


    @GetMapping("/exists")
    @Operation(summary = "Returns true if at least one airline with given name exists.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "'True' if exists, else 'False'.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
    })
    public boolean exists(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        return airlineService.exists(name);
    }

    
    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK, reason = "Airline deleted.")
    @Operation(summary = "Delete airline by name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Airline deleted.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid name.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Airline with this name does not exist or has related entites.", content = {@Content(mediaType = "application/json")}),
    })
    public void delete(@NotBlank(message = "Cannot leave parameter 'name' empty.") @RequestParam String name) {

        airlineService.delete(name);
    }
}