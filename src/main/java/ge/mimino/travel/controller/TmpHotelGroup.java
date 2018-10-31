package ge.mimino.travel.controller;

public class TmpHotelGroup {
  private Integer hotelId;
  private Integer groupId;

  public TmpHotelGroup(Integer hotelId, Integer groupId) {
    this.hotelId = hotelId;
    this.groupId = groupId;
  }

  public TmpHotelGroup() {
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
