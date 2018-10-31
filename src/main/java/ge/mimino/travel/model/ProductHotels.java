package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_hotels")
public class ProductHotels {
  private Integer id;
  private Integer hotelId;
  private Integer requestId;
  private Integer day;
  private Integer groupId;

  public ProductHotels() {
  }

  public ProductHotels(Integer hotelId, Integer requestId, Integer day, Integer groupId) {
    this.hotelId = hotelId;
    this.requestId = requestId;
    this.day = day;
    this.groupId = groupId;
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "hotel_id")
  public Integer getHotelId() {
    return hotelId;
  }

  public void setHotelId(Integer hotelId) {
    this.hotelId = hotelId;
  }

  @Basic
  @Column(name = "request_id")
  public Integer getRequestId() {
    return requestId;
  }

  public void setRequestId(Integer requestId) {
    this.requestId = requestId;
  }

  @Basic
  @Column(name = "day")
  public Integer getDay() {
    return day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  @Basic
  @Column(name = "group_id")
  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }
}
