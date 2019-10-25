package com.example.demo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.example.manager.FlightManager;
import com.example.ro.Flight;
import com.example.ro.TicketTotalResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {

    private FlightManager manager = new FlightManager();

    @GetMapping("/flights/flight")
    public Flight getFlight() throws ParseException {
        return manager.getFlight();
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() throws ParseException {
        List<Flight> flights =  new ArrayList<Flight>();
        flights.add(manager.getFlights().get(1));
        return flights;
    }

    @PostMapping(value = "/flights/tickets/total", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TicketTotalResponse getTicketTotal(@RequestBody Flight body) throws ParseException {
        TicketTotalResponse result = new TicketTotalResponse();
        result.setResult(manager.getTicketTotalForFlight(body));
        return result;
    }
    
}