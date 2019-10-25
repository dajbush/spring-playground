package com.example.demo;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.ro.Flight;
import com.example.ro.Passenger;
import com.example.ro.Ticket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTests {

    @Autowired
    MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testFlightTicketTotalWithStringLiteral() throws Exception {
        RequestBuilder request = post("/flights/tickets/total")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(getJSONStringLiteral());
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    public void testFlightTicketTotalWithSerializing() throws Exception {
        RequestBuilder request = post("/flights/tickets/total")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(getJSONSerialized());
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    public void testFlightTicketTotalFromDataFile() throws Exception {
        String json = getJSONFromFile("/data.json");

        RequestBuilder request = post("/flights/tickets/total")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json);
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result", is(351)));
    }

    @Test
    public void testFlight() throws Exception {
        this.mvc.perform(
            get("/flights/flight")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
            .andExpect(jsonPath("$.tickets[0].price", is(200)))
            .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Oliver")))
            .andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Queen")));
    }

    @Test
    public void testFlights() throws Exception {
        this.mvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //check first flight
                // .andExpect(jsonPath("$[0].departs", is("2017-04-21 14:34")))
                // .andExpect(jsonPath("$[0].tickets[0].price", is(200)))
                // .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("Oliver")))
                // .andExpect(jsonPath("$[0].tickets[0].passenger.lastName", is("Queen")));
                //check second flight
                // .andExpect(jsonPath("$[1].departs", is("2017-04-21 14:34")))
                // .andExpect(jsonPath("$[1].tickets[0].price", is(400)))
                // .andExpect(jsonPath("$[1].tickets[0].passenger.firstName", is("John")))
                // .andExpect(jsonPath("$[1].tickets[0].passenger.lastName").doesNotExist());
                //test second flight as only element
                .andExpect(jsonPath("$[0].departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].tickets[0].price", is(400)))
                .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("John")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.lastName").doesNotExist());
    }

    private String getJSONStringLiteral() {
        return "{ \"tickets\": [ { \"passenger\": { \"firstName\": \"Some name\", \"lastName\": \"Some other name\" }, \"price\": 200 },"
                + "{ \"passenger\": { \"firstName\": \"Name B\", \"lastName\": \"Name C\" }, \"price\": 150 } ] }";
    }

    private String getJSONSerialized() throws JsonProcessingException {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("tickets", getFlight().getTickets());
        return objectMapper.writeValueAsString(data); 
    }

    private String getJSONFromFile(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));
    }

    public Flight getFlight() {
        Flight flight = new Flight();
        List<Ticket> tickets = new ArrayList<Ticket>();
        Passenger passenger1 = new Passenger();
        Passenger passenger2 = new Passenger();

        passenger1.setFirstName("Oliver");
        passenger1.setLastName("Queen");
        passenger2.setFirstName("John");
        passenger2.setLastName("Dorian");

        Ticket ticket1 = new Ticket();
        ticket1.setPrice(200);
        ticket1.setPassenger(passenger1);
        tickets.add(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setPrice(150);
        ticket2.setPassenger(passenger1);
        tickets.add(ticket2);

        flight.setTickets(tickets);

        return flight;

    }
    
}