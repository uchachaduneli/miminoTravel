package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_places", schema = "mimino")
public class ProductPlaces {
  private Integer id;
  private Place place;
  private Integer requestId;
  private Integer day;

  public ProductPlaces() {
  }

  public ProductPlaces(Place place, Integer requestId, Integer day) {
    this.place = place;
    this.requestId = requestId;
    this.day = day;
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

  @JoinColumn(name = "place_id")
  @OneToOne
  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
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
}
