package com.example.myAirlineFlightservice.models;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Enum representing a type of seat on a flight.
 * 
 * @since 0.0.1
 */
@Getter
@AllArgsConstructor
public enum SeatType {
    NORMAL(0.0),
    CORRIDOR(5.0),
    WINDOW(5.0),
    FOOT_ROOM(5.0);

    private double seatFee;


    public void setSeatFee(@DecimalMin("0.0") double seatFee) {

        this.seatFee = seatFee;
    }
}