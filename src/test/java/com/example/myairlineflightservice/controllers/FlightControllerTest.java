package com.example.myAirlineFlightservice.controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.myAirlineFlightservice.models.Flight;
import com.example.myAirlineFlightservice.models.FlightClass;
import com.example.myAirlineFlightservice.services.FlightService;
import jakarta.servlet.ServletException;


/**
 * Test class for {@link FlightController}.
 * 
 * @since 0.0.1
 */
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @MockBean
    FlightService flightService;

    @Autowired
    MockMvc mockMvc;

    @Value("${baseUrl}")
    String baseUrl;

    Flight flight;


    @BeforeEach
    void setUp() {

        this.flight = new Flight(6l, 
                                 5l, 
                                 "Lufthansa", 
                                 "Munich airport", 
                                 "Hamburg airport", 
                                 LocalTime.of(10, 0), 
                                 LocalTime.of(13, 0), 
                                 LocalDate.of(2023, 4, 1), 
                                 LocalDate.of(2023, 4, 1), 
                                 new HashSet<>(Set.of(FlightClass.ECONOMY)), 
                                 30.0, 
                                 40, 
                                 10, 10, 
                                 10, 
                                 70);
    }


    @Test
    void getById_shouldThrowInvalidId() throws Exception {

        assertThrows(ServletException.class, () -> mockMvc.perform(get(baseUrl + "/flight/getById/-1")));
    }


    @Test
    void getById_shouldBeValidId() throws Exception {
        
        when(flightService.getById(1l)).thenReturn(flight);

        mockMvc.perform(get(baseUrl + "/flight/getById/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"));
    }


    @Test
    void delete_shouldThrowInvalidNumber() throws Exception {

        assertThrows(ServletException.class, () -> mockMvc.perform(delete(baseUrl + "/flight/delete?number=-1")));
    }


    @Test
    void delete_shouldBeValidNumber() throws Exception {
        
        mockMvc.perform(delete(baseUrl + "/flight/delete?number=1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}