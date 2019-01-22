package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_hotels")
public class ProductHotels {
  private Integer id;
  private Hotel hotel;
  private Integer requestId;
  private Integer day;
  private Integer groupId;

  public ProductHotels() {
  }

  public ProductHotels(Hotel hotel, Integer requestId, Integer day, Integer groupId) {
    this.hotel = hotel;
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

  @JoinColumn(name = "hotel_id")
  @OneToOne
  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
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
