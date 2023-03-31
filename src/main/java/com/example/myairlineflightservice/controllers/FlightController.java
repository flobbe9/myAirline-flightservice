package com.example.myAirlineFlightservice.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
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

import com.example.myAirlineFlightservice.annotations.ValidFlight;
import com.example.myAirlineFlightservice.models.Flight;
import com.example.myAirlineFlightservice.models.FlightClass;
import com.example.myAirlineFlightservice.services.FlightService;
import com.example.myAirlineFlightservice.utils.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * Class handling endpoints related to the {@link Flight} entity.
 * 
 * @since 0.0.1
 */
@RestController
@RequestMapping("/flight")
@Validated
public class FlightController {
    
    @Autowired
    private FlightService flightService;


    @GetMapping("/getById/{id}") 
    @Operation(summary = "Get flight by id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found flight.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid id.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Flight with this id not found.", content = {@Content(mediaType = "application/json")}),
    })
    public Flight getById(@NotNull 
                          @Min(value = 1, message = "Id must be greater than 0.")
                          @PathVariable long id) {

        return flightService.getById(id);
    }


    @GetMapping("/getByNumber")
    @Operation(summary = "Get flight by number.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found flight.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid number.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Flight with this number not found.", content = {@Content(mediaType = "application/json")}),
    })
    public Flight getByNumber(@NotNull 
                              @Min(value = 0, message = "Number cannot be negative.")
                              @RequestParam long number) {

        return flightService.getByNumber(number);
    }


    @GetMapping("/getAllByDepartureTime")
    @Operation(summary = "Get all flights by departure time.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found a flight list (might be empty).", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid time.", content = {@Content(mediaType = "application/json")}),
    })
    public List<Flight> getAllByDepartureTime(@NotNull(message = "Departure time cannot be null.") 
                                              @RequestParam LocalTime departureTime) {

        return flightService.getAllByDepartureTimeAfter(departureTime);
    }


    @GetMapping("/getAllByDepartureDate")
    @Operation(summary = "Get all flights by departure date.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found a flight list (might be empty).", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid date.", content = {@Content(mediaType = "application/json")}),
    })
    public List<Flight> getAllByDepartureDate(@NotNull(message = "Departure date cannot be null.")
                                             @RequestParam LocalDate departureDate) {

        return flightService.getAllByDepartureDate(departureDate);
    }


    @GetMapping("/getAllByAirport")
    @Operation(summary = "Get all flights by departure and/or arrival airport.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found a flight list (might be empty).", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid airport.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Airport not found.", content = {@Content(mediaType = "application/json")}),
    })
    public List<Flight> getAllByAirport(@NotBlank(message = "Airport name cannot be blank.")
                                        @RequestParam String airportName) {
        
        return flightService.getAllByAirport(airportName);
    }


    @GetMapping("/getAllByBasePrice")
    @Operation(summary = "Get all flights cheaper or equal than given base price.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found a flight list (might be empty).", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid base price.", content = {@Content(mediaType = "application/json")}),
    })
    public List<Flight> getAllByBasePriceLessThanEqual(@Min(value = 0, message = "Base price cannot be negative.")
                                                       @RequestParam double basePrice) {

        return flightService.getAllByBasePriceLessThanEqual(basePrice);
    }


    @GetMapping("/getAllBySeatsTotal")
    @Operation(summary = "Get all flights by total seats greater than or equal.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found a flight list (might be empty).", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid seat number.", content = {@Content(mediaType = "application/json")}),
    })
    public List<Flight> getAllBySeatsTotalGreaterThanEqual(@Min(value = 0, message = "Seats total cannot be negative.")
                                                           @RequestParam int seatsTotal) {

        return flightService.getAllByBasePriceLessThanEqual(seatsTotal);
    }


    @GetMapping("/getAllByFlightClass")
    @Operation(summary = "Get all flights by flight class.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found a flight list (might be empty).", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid flightClass.", content = {@Content(mediaType = "application/json")}),
    })
    public List<Flight> getAllByFlightClass(@NotNull(message = "Flight class cannot be null.") 
                                            @RequestParam FlightClass flightClass) {

        return flightService.getAllByFlightClass(flightClass);
    }


    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.OK, reason = "Flight saved.")
    @Operation(summary = "Save given flight in db.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Flight saved.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid flight.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Flight already exists.", content = {@Content(mediaType = "application/json")}),
    })
    public void save(@Validated(ValidFlight.class) @RequestBody Flight flight, Errors errors) {

        if (errors.hasErrors()) 
            throw new ApiException(errors.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);

        flightService.save(flight);
    }


    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK, reason = "Flight updated.")
    @Operation(summary = "Replace existing flight in db with given flight (must have same id).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Flight updated.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid flight.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Flight does not exist.", content = {@Content(mediaType = "application/json")}),
    })
    public void update(@Validated(ValidFlight.class) @RequestBody Flight flight, Errors errors) {
        
        if (errors.hasErrors()) 
            throw new ApiException(errors.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);

        flightService.update(flight);
    }


    @PutMapping("/updateAllByAirportName")
    @ResponseStatus(code = HttpStatus.OK, reason = "Flights updated.")
    @Operation(summary = "Update airport names of all flights related to this airport.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Flights updated.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid flight.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Flight or airport does not exist.", content = {@Content(mediaType = "application/json")}),
    })
    public void updateAllByAirportName(@NotBlank(message = "Airport name cannot be blank.") @RequestParam String oldAirportName,
                                       @NotBlank(message = "Airport name cannot be blank.") @RequestParam String newAirportName) {

        flightService.updateAllByAirportName(oldAirportName, newAirportName);
    }


    @GetMapping("/exists")
    @Operation(summary = "Returns true if at least one flight with given number exists.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "'True' if exists, else 'False'.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid number.", content = {@Content(mediaType = "application/json")}),
    })
    public boolean exists(@Min(value = 0, message = "Number cannot be negative.") @RequestParam long number) {

        return flightService.exists(number);
    }


    @GetMapping("/existsByAirport")
    @Operation(summary = "Returns true if at least one flight related to given airoprt exists.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "'True' if exists, else 'False'.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid airport name.", content = {@Content(mediaType = "application/json")}),
    })
    public boolean existsByAirport(@NotBlank(message = "Airport name cannot be blank.") @RequestParam String airportName) {

        return flightService.existsByAirport(airportName);
    }

    
    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK, reason = "Flight deleted.")
    @Operation(summary = "Delete flight by number.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Flight deleted.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid number.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", description = "Flight with this number does not exist.", content = {@Content(mediaType = "application/json")}),
    })
    public void delete(@Min(value = 0, message = "Number must not be negative.") @RequestParam long number) {

        flightService.delete(number);
    }


    @DeleteMapping("/deleteAllByAirport")
    @ResponseStatus(code = HttpStatus.OK, reason = "Flight deleted.")
    @Operation(summary = "Delete all flights related to given airport.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Flights deleted.", content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400", description = "Invalid airport name.", content = {@Content(mediaType = "application/json")}),
    })
    public void deleteAllByAirport(@NotBlank(message = "Airport name cannot be blank.") @RequestParam String airportName) {
        
        flightService.deleteAllByAirportName(airportName);
    }
}