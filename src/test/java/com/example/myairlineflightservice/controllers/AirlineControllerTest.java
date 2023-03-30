package com.example.myAirlineFlightservice.controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.myAirlineFlightservice.models.Airline;
import com.example.myAirlineFlightservice.services.AirlineService;
import com.example.myAirlineFlightservice.utils.HttpRequestSender;

import jakarta.servlet.ServletException;


/**
 * Test class for {@link AirlineController}.
 * 
 * @since 0.0.1
 */
@WebMvcTest(AirlineController.class)
public class AirlineControllerTest {

    @MockBean
    AirlineService airlineService;

    @Autowired
    MockMvc mockMvc;

    Airline airline;


    @BeforeEach
    void setUp() {

        this.airline = new Airline("Munich airline");
    }


    @Test
    void getById_shouldThrowInvalidId() throws Exception {

        assertThrows(ServletException.class, () -> mockMvc.perform(get(HttpRequestSender.BASE_URL + "/airline/getById/-1")));
    }


    @Test
    void getById_shouldBeValidId() throws Exception {
        
        when(airlineService.getById(1l)).thenReturn(airline);

        mockMvc.perform(get(HttpRequestSender.BASE_URL + "/airline/getById/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"));
    }


    @Test
    void delete_shouldThrowInvalidName() throws Exception {

        assertThrows(ServletException.class, () -> mockMvc.perform(delete(HttpRequestSender.BASE_URL + "/airline/delete?name=")));
    }


    @Test
    void delete_shouldBeValidName() throws Exception {
        
        mockMvc.perform(delete(HttpRequestSender.BASE_URL + "/airline/delete?name=Munich airline"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
