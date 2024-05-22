package com.prasad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class BookingController {

    private final String providerApiUrl = "localhost:9090/ticket"; // Replace with actual provider API URL

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/booking")
    public String booking() {
        return "booking";
    }

    @PostMapping("/book")
    public String book(
            @RequestParam String name,
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String doj,
            @RequestParam String trainNumber,
            Model model) {

        RestTemplate restTemplate = new RestTemplate();
        BookingRequest bookingRequest = new BookingRequest(name, from, to, doj, trainNumber);
        
        String response = restTemplate.postForObject(providerApiUrl, bookingRequest, String.class);
        
        model.addAttribute("response", response);
        return "result";
    }
}

class BookingRequest {
    private String name;
    private String from;
    private String to;
    private String doj;
    private String trainNumber;

    // Constructors, getters, and setters

    public BookingRequest(String name, String from, String to, String doj, String trainNumber) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.doj = doj;
        this.trainNumber = trainNumber;
    }

    // getters and setters
}
