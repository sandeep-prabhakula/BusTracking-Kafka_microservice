package com.sandeepprabhakula.kafkaproducer.model;
public class BusPayload {
    private String busNumber;
    private Coordinates coordinates;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }


}
