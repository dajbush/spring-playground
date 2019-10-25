package com.example.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.ro.Flight;
import com.example.ro.Passenger;
import com.example.ro.Ticket;

public class FlightManager {
    
    public List<Flight> getFlights() throws ParseException {
        List<Flight> flights = new ArrayList<Flight>();
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        List<Ticket> tickets1 = new ArrayList<Ticket>();
        List<Ticket> tickets2 = new ArrayList<Ticket>();
        Passenger passenger1 = new Passenger();
        Passenger passenger2 = new Passenger();

        String dateStr1 = "2017-04-21 14:34";
        DateFormat format = new SimpleDateFormat("yyy-MM-dd kk:mm");
        Date date1 = format.parse(dateStr1);

        passenger1.setFirstName("Oliver");
        passenger1.setLastName("Queen");
        passenger2.setFirstName("John");

        Ticket ticket1 = new Ticket();
        ticket1.setPrice(200);
        ticket1.setPassenger(passenger1);
        tickets1.add(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setPrice(400);
        ticket2.setPassenger(passenger2);
        tickets2.add(ticket2);

        flight1.setDepartOn(date1);
        flight1.setTickets(tickets1);

        flight2.setDepartOn(date1);
        flight2.setTickets(tickets2);

        flights.add(flight1);
        flights.add(flight2);

        return flights;

    }

    public Flight getFlight() throws ParseException {
        Flight flight = new Flight();
        List<Ticket> tickets = new ArrayList<Ticket>();
        Passenger passenger1 = new Passenger();

        String dateStr = "2017-04-21 14:34";
        DateFormat format = new SimpleDateFormat("yyy-MM-dd kk:mm");
        Date date = format.parse(dateStr);

        passenger1.setFirstName("Oliver");
        passenger1.setLastName("Queen");

        Ticket ticket1 = new Ticket();
        ticket1.setPrice(200);
        ticket1.setPassenger(passenger1);
        tickets.add(ticket1);

        flight.setDepartOn(date);
        flight.setTickets(tickets);

        return flight;

    }

    public int getTicketTotalForFlight(Flight flight) {
        List<Ticket> tickets = flight.getTickets();
        int total = 0;

        for (Ticket ticket : tickets) {
            total += ticket.getPrice();
        }
        return total;
    }
    
}