package ge.mimino.travel.controller;

import ge.mimino.travel.model.Hotel;

public class TmpHotelGroup {
  private Integer hotelId;
  private Hotel hotel;
  private Integer groupId;

  public TmpHotelGroup(Hotel hotel, Integer groupId) {
    this.hotel = hotel;
    this.hotelId = hotel.getId();
    this.groupId = groupId;
  }

  public TmpHotelGroup() {
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  public Integer getHotelId() {
    return hotelId;
  }

  public void setHotelId(Integer hotelId) {
    this.hotelId = hotelId;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

}
