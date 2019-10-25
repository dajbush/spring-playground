package com.example.demo;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTests {

    @Autowired
    MockMvc mvc;
    
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
    
}