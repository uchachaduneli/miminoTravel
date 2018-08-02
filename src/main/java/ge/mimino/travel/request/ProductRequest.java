package ge.mimino.travel.request;

import java.util.List;

public class ProductRequest {
    private List<Integer> sights;
    private List<Integer> places;
    private List<Integer> hotels;
    private List<Integer> transports;
    private List<Integer> nonstandarts;
    private Integer day;
    private Integer requestId;

    public List<Integer> getSights() {
        return sights;
    }

    public void setSights(List<Integer> sights) {
        this.sights = sights;
    }

    public List<Integer> getPlaces() {
        return places;
    }

    public void setPlaces(List<Integer> places) {
        this.places = places;
    }

    public List<Integer> getHotels() {
        return hotels;
    }

    public void setHotels(List<Integer> hotels) {
        this.hotels = hotels;
    }

    public List<Integer> getTransports() {
        return transports;
    }

    public void setTransports(List<Integer> transports) {
        this.transports = transports;
    }

    public List<Integer> getNonstandarts() {
        return nonstandarts;
    }

    public void setNonstandarts(List<Integer> nonstandarts) {
        this.nonstandarts = nonstandarts;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
}
