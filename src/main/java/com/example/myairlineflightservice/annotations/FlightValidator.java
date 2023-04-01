package com.example.myAirlineFlightservice.annotations;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

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
@Validated
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

        // set default message
        context.disableDefaultConstraintViolation();

        // set new default message
        return isFlightValid(flight, context);
    }
    

    /**
     * Check fields of flight in the db and call all neccessary validation methods
     * not covered by jakarta.
     * 
     * @param flight to check.
     * @param context to set the error message
     * @return true if all validations are true.
     */
    private boolean isFlightValid(Flight flight, ConstraintValidatorContext context) {

        return
            // related entities should exist
            relatedEntitiesDoExist(flight, context) &&

            // dates should be in order
            areDatesInOrder(flight, context) &&

            // time should be in order
            isTimeInOrder(flight, context) &&

            // number of seats should fit
            isNumAvailableSeatsValid(flight, context);
    }


    /**
     * Checks that related entites of flight exist in db already.
     * 
     * @param flight to check the related entites of
     * @param context to set the error message
     * @return true if all related entities exist
     */
    private boolean relatedEntitiesDoExist(Flight flight, ConstraintValidatorContext context) {

        // airline and airports should exist
        boolean doesContextExist = airlineService.exists(flight.getAirlineName()) && 
                                    airportService.exists(flight.getDepartureAirportName()) && 
                                    airportService.exists(flight.getArrivalAirportName());

        if (!doesContextExist) {
            context.buildConstraintViolationWithTemplate("Flight invalid: At least one related entity does not exist.").addConstraintViolation();
            return false;
        }

        return true;
    }


    /**
     * Departure date should be the same or after arrival date.
     * 
     * @param flight to check the dates of.
     * @param context to set the error message
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
     * @param context to set the error message
     * @return true if time is in order.
     */
    private boolean isTimeInOrder(Flight flight, ConstraintValidatorContext context) {

        boolean isTimeInOrder = flight.getDepartureTime().isBefore(flight.getArrivalTime());

        // check only if dates are the same
        if (flight.getDepartureDate().equals(flight.getArrivalDate()) && !isTimeInOrder) {
            context.buildConstraintViolationWithTemplate("Flight invalid: Time out of order.").addConstraintViolation();

            return false;
        }
        
        return true;
    }


    /**
     * Number of seats should add up to number of available seats.
     * 
     * @param flight to check
     * @param context to set the error message
     * @return true if number of available seats equals the sum of all other seat categories.
     */
    private boolean isNumAvailableSeatsValid(Flight flight, ConstraintValidatorContext context) {

        boolean isNumAvailableSeatsValid = flight.getNumNormalSeats() + 
                                            flight.getNumCorridorSeats() + 
                                            flight.getNumWindowSeats() + 
                                            flight.getNumFootRoomSeats() == flight.getNumAvailableSeats();
        if (!isNumAvailableSeatsValid)                            
            context.buildConstraintViolationWithTemplate("Flight invalid: Falsy number of available seats.").addConstraintViolation();

        return isNumAvailableSeatsValid;
    }
}