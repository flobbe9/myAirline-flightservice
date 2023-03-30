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

import com.example.myAirlineFlightservice.models.Country;
import com.example.myAirlineFlightservice.services.CountryService;
import jakarta.servlet.ServletException;


/**
 * Test class for {@link CountryController}.
 * 
 * @since 0.0.1
 */
@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @MockBean
    CountryService countryService;

    @Autowired
    MockMvc mockMvc;

    @Value("${baseUrl}")
    String baseUrl;

    Country country;


    @BeforeEach
    void setUp() {

        this.country = new Country("Germany");
    }


    @Test
    void getById_shouldThrowInvalidId() throws Exception {

        assertThrows(ServletException.class, () -> mockMvc.perform(get(baseUrl + "/country/getById/-1")));
    }


    @Test
    void getById_shouldBeValidId() throws Exception {
        
        when(countryService.getById(1l)).thenReturn(country);

        mockMvc.perform(get(baseUrl + "/country/getById/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"));
    }


    @Test
    void delete_shouldThrowInvalidName() throws Exception {

        assertThrows(ServletException.class, () -> mockMvc.perform(delete(baseUrl + "/country/delete?name=")));
    }


    @Test
    void delete_shouldBeValidName() throws Exception {
        
        mockMvc.perform(delete(baseUrl + "/country/delete?name=Germany"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
