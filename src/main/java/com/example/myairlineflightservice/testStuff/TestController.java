package com.example.myAirlineFlightservice.testStuff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RestController
@CrossOrigin
public class TestController {

    @Autowired
    private TestRepository testRepository;


    @PostMapping("/flight/search")
    public List<FlightDetailsWrapper> getFlight(@RequestBody SearchInput wrapper) {

        FlightDetailsWrapper fdw = new FlightDetailsWrapper(wrapper.getFrom(),
                                                            wrapper.getTo(),
                                                            wrapper.getDate(),
                                                            wrapper.getDate(),
                                                            wrapper.getTime(),
                                                            wrapper.getTime(), 1);

        return List.of(fdw, fdw, fdw, fdw, fdw);
    }


    @PostMapping("/flight/details/{id}")
    public FlightDetailsWrapper getFlightDetails(@PathVariable Integer id) {

        FlightDetailsWrapper fdw = new FlightDetailsWrapper("Hamburg",
                                                            "München",
                                                            "2023-03-02",
                                                            "2023-03-02",
                                                            "20:00",
                                                            "23:00", 
                                                             id);

        return fdw;
    }


    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.OK, reason = "Successfully registered.")
    public void registerUser(@RequestBody UserDetailsWrapper userDetails) {

        String email = userDetails.getEmail();

        // email exists
        if (testRepository.exists(email))
            throw new IllegalStateException("This email has already been registered. Please try a different one.");

        testRepository.save(email);
    }


    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK, reason = "Successfully logged in.")
    public void login(@RequestBody LoginWrapper loginWrapper) {

        if (loginWrapper.getPassword() == null) 
            throw new IllegalStateException("Password missing.");

        // check if email exists (ignore password for now)
        if (!testRepository.exists(loginWrapper.getEmail()))
            throw new IllegalStateException("Wrong email or password. Please try again.");
    }


    @GetMapping("/")
    public String index() {

        return "index";
    }


    @Getter
    @Setter
    @AllArgsConstructor
    static class SearchInput {
        private String from;
        private String to;
        private String date;
        private String time;

        public SearchInput() {}


    }


    @Getter
    @Setter
    @RequiredArgsConstructor
    static class FlightDetailsWrapper {
        private final String departureAirport;
        private final String arrivalAirport;
        private final String departureDate;
        private final String arrivalDate;
        private final String departureTime;
        private final String arrivalTime;
        private final int id;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    static class UserDetailsWrapper {
        private String email;
        private String firstName;
        private String surName;
        private String password;

        public UserDetailsWrapper() {}
    }


    @Getter
    @Setter
    @AllArgsConstructor
    static class LoginWrapper {
        private String email;
        private String password;

        public LoginWrapper() {}
    }
}