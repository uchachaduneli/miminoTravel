package ge.mimino.travel.request;

import ge.mimino.travel.dto.GeoObjectDTO;
import ge.mimino.travel.dto.ProductRestaurantsDTO;

import java.util.List;

public class ProductRequest {
    private List<Integer> regions;
    private List<GeoObjectDTO> sights;
    private List<Integer> places;
    private List<Integer> hotels;
    private List<Integer> transports;
    private List<Integer> nonstandarts;
    private List<ProductRestaurantsDTO> restaurants;
    private Integer day;
    private Integer requestId;

    public List<Integer> getRegions() {
        return regions;
    }

    public void setRegions(List<Integer> regions) {
        this.regions = regions;
    }

    public List<GeoObjectDTO> getSights() {
        return sights;
    }

    public void setSights(List<GeoObjectDTO> sights) {
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

    public List<ProductRestaurantsDTO> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<ProductRestaurantsDTO> restaurants) {
        this.restaurants = restaurants;
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
