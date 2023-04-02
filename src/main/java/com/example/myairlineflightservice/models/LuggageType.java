package com.example.myAirlineFlightservice.models;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Enum representing a type of luggage a user can book for their flight.
 * 
 * @since 0.0.1
 */
@Getter
@AllArgsConstructor
public enum LuggageType {
    CABBIN_LUGGAGE(0.0),
    ADDITIONAL_LUGGAGE(35.0);

    private double fee;


    public void setFee(@DecimalMin("0.0") double fee) {

        this.fee = fee;
    }
}