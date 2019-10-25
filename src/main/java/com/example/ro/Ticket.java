package com.example.ro;

public class Ticket {
    private Passenger passenger;
    private int price;

    public Ticket() {
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket [passenger=" + passenger + ", price=" + price + "]";
    }
    
}