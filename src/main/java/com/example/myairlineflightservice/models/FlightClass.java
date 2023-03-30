package com.example.myAirlineFlightservice.models;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Enum listing the flight classes and the fee they cost when booked.
 * 
 * @since 0.0.1
 */
@Getter
@AllArgsConstructor
public enum FlightClass {

    ECONOMY(0.0),
    BUSINESS(30.0);

    private Double fee;


    public void setFee(@Min(value = 0, message = "Fee cannot be negative.") double fee) {

        this.fee = fee;
    }
}