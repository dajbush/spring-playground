package com.example.ro;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Flight {
    @JsonProperty("departs")
    @JsonFormat(pattern = "yyy-MM-dd HH:mm", timezone = "America/Chicago")
    private Date departOn;
    @JsonProperty("tickets")
    private List<Ticket> tickets;

    public Flight() {
    }

    public Date getDepartOn() {
        return departOn;
    }

    public void setDepartOn(Date departOn) {
        this.departOn = departOn;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
}