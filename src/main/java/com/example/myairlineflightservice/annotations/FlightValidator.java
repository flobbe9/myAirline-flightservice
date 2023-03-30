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
        return isFlightValid(flight, context);
    }
    

    /**
     * Check fields of flight in the db and call all neccessary validation methods
     * not covered by jakarta.
     * 
     * @param flight to check.
     * @return true if all validations are true.
     */
    private boolean isFlightValid(Flight flight, ConstraintValidatorContext context) {

        return 
            // airline should exist
            airlineService.exists(flight.getAirlineName()) &&

            // airports should exist
            airportService.exists(flight.getDepartureAirportName()) &&
            airportService.exists(flight.getArrivalAirportName()) &&

            // dates should be in order
            areDatesInOrder(flight, context) &&

            // time should be in order
            isTimeInOrder(flight, context) &&

            // number of seats should fit
            isTotalSeatsValid(flight, context);
    }


    /**
     * Departure date should be the same or after arrival date.
     * 
     * @param flight to check the dates of.
     * @return true if dates are in order.
     */
    private boolean areDatesInOrder(Flight flight, ConstraintValidatorContext context) {

        LocalDate departureDate = flight.getDepartureDate();
        LocalDate arrivalDate = flight.getArrivalDate();

        boolean areDatesInOrder = departureDate.equals(arrivalDate) || departureDate.isBefore(arrivalDate);

        if (!areDatesInOrder) 
            context.buildConstraintViolationWithTemplate("Flight invalid: Dates out of order.").addConstraintViolation();
        
        return areDatesInOrder;
    }


    /**
     * Is checked <strong>only</strong> if departure and arrival date are the same.
     * In that case the departure time should be before the arrival time.
     * 
     * @param flight to check the time of.
     * @return true if time is in order.
     */
    private boolean isTimeInOrder(Flight flight, ConstraintValidatorContext context) {

        boolean isTimeInOrder = flight.getDepartureTime().isBefore(flight.getArrivalTime());

        // check only if dates are the same
        if (flight.getDepartureDate().equals(flight.getArrivalDate())) {
            context.buildConstraintViolationWithTemplate("Flight invalid: Time out of order.").addConstraintViolation();

            return isTimeInOrder;
        }
        
        return true;
    }


    /**
     * Number of seats should add up to total seats.
     * 
     * @param flight
     * @return true if total seats equals the sum of all other seat categories.
     */
    private boolean isTotalSeatsValid(Flight flight, ConstraintValidatorContext context) {

        boolean isTotalSeatsValid = flight.getNumNormalSeats() + 
                                    flight.getNumCorridorSeats() + 
                                    flight.getNumWindowSeats() + 
                                    flight.getNumFootRoomSeats() == flight.getSeatsTotal();
        if (!isTotalSeatsValid)                            
            context.buildConstraintViolationWithTemplate("Flight invalid: Wrong number of total seats.").addConstraintViolation();

        return isTotalSeatsValid;
    }
}