package com.example.myAirlineFlightservice.annotations;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myAirlineFlightservice.models.Flight;
import com.example.myAirlineFlightservice.services.AirlineService;
import com.example.myAirlineFlightservice.services.AirportService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


/**
 * Validation class for {@link Flight}.
 * 
 * @since 0.0.1
 */
public class FlightValidator implements ConstraintValidator<ValidFlight, Flight> {

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirportService airportService;


    @Override
    public void initialize(ValidFlight constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    /**
     * Calls validation methods and sets default error message.
     * 
     * @param flight to validate.
     * @param context holding the error message.
     * @return true if validation was successful.
     */
    @Override
    public boolean isValid(Flight flight, ConstraintValidatorContext context) {

        // disable default message
        context.disableDefaultConstraintViolation();

        // set new default message
        if (!isFlightValid(flight)) {
            context.buildConstraintViolationWithTemplate("Flight invalid.")
                   .addConstraintViolation();
            
            return false;
        }

        return true;
    }
    

    /**
     * Check fields of flight in the db and call all neccessary validation methods
     * not covered by jakarta.
     * 
     * @param flight to check.
     * @return true if all validations are true.
     */
    private boolean isFlightValid(Flight flight) {

        return 
            // airline should exist
            airlineService.exists(flight.getAirlineName()) &&

            // airports should exist
            airportService.exists(flight.getDepartureAirportName()) &&
            airportService.exists(flight.getArrivalAirportName()) &&

            // dates should be in order
            areDatesInOrder(flight) &&

            // time should be in order
            isTimeInOrder(flight);
    }


    /**
     * Departure date should be the same or after arrival date.
     * 
     * @param flight to check the dates of.
     * @return true if dates are in order.
     */
    private boolean areDatesInOrder(Flight flight) {

        LocalDate departureDate = flight.getDepartureDate();
        LocalDate arrivalDate = flight.getArrivalDate();

        return departureDate.equals(arrivalDate) || departureDate.isBefore(arrivalDate);
    }


    /**
     * Is checked <strong>only</strong> if departure and arrival date are the same.
     * In that case the departure time should be before the arrival time.
     * 
     * @param flight to check the time of.
     * @return true if time is in order.
     */
    private boolean isTimeInOrder(Flight flight) {

        if (flight.getDepartureDate().equals(flight.getArrivalDate()))
            return flight.getDepartureTime().isBefore(flight.getArrivalTime());

        return true;
    }
}