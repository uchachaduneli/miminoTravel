package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_transports")
public class ProductTransports {
  private Integer id;
  private Integer transportId;
  private Integer requestId;
  private Integer count;
  private Integer touristCount;

  public ProductTransports() {
  }

  public ProductTransports(Integer transportId, Integer requestId, Integer count, Integer touristCount) {
    this.transportId = transportId;
    this.requestId = requestId;
    this.count = count;
    this.touristCount = touristCount;
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
  @Column(name = "transport_id")
  public Integer getTransportId() {
    return transportId;
  }

  public void setTransportId(Integer transportId) {
    this.transportId = transportId;
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
  @Column(name = "count")
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  @Basic
  @Column(name = "touristCount")
  public Integer getTouristCount() {
    return touristCount;
  }

  public void setTouristCount(Integer touristCount) {
    this.touristCount = touristCount;
  }
}
