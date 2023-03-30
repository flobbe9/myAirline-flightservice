package com.example.myAirlineFlightservice.controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.myAirlineFlightservice.models.City;
import com.example.myAirlineFlightservice.services.CityService;
import jakarta.servlet.ServletException;


/**
 * Test class for {@link CityController}.
 * 
 * @since 0.0.1
 */
@WebMvcTest(CityController.class)
public class CityControllerTest {

    @MockBean
    CityService cityService;

    @Autowired
    MockMvc mockMvc;

    @Value("${baseUrl}")
    String baseUrl;

    City city;


    @BeforeEach
    void setUp() {

        this.city = new City("Munich", "Germany");
    }


    @Test
    void getById_shouldThrowInvalidId() throws Exception {

        assertThrows(ServletException.class, () -> mockMvc.perform(get(baseUrl + "/city/getById/-1")));
    }


    @Test
    void getById_shouldBeValidId() throws Exception {
        
        when(cityService.getById(1l)).thenReturn(city);

        mockMvc.perform(get(baseUrl + "/city/getById/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"));
    }


    @Test
    void delete_shouldThrowInvalidName() throws Exception {

        assertThrows(ServletException.class, () -> mockMvc.perform(delete(baseUrl + "/city/delete?name=")));
    }


    @Test
    void delete_shouldBeValidName() throws Exception {
        
        mockMvc.perform(delete(baseUrl + "/city/delete?name=Munich"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
