package com.example.myAirlineFlightservice.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class representing an airline.
 * 
 * @since 0.0.1
 */
@Getter
@Setter
@AllArgsConstructor
public class Airline {

    @NotBlank
    private String name;



    // RYAN_AIR,
    // WIZZ_AIR,
    // TYRKISH_AIRLINES,
    // QATAR_AIRLINES,
    // LUFTHANSA
}
