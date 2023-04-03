package com.example.myAirlineFlightservice.models;

import jakarta.validation.constraints.DecimalMin;
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
    BUSINESS(30.0),
    FIRST(50.0);

    private Double fee;


    public void setFee(@DecimalMin(value = "0.0", message = "Fee cannot be negative.") double fee) {

        this.fee = fee;
    }
}