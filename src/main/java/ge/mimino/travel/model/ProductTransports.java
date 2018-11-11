package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_transports")
public class ProductTransports {
  private Integer id;
  private Transport transport;
  private Integer requestId;
  private Integer count;
  private Integer touristCount;

  public ProductTransports() {
  }

  public ProductTransports(Transport transport, Integer requestId, Integer count, Integer touristCount) {
    this.transport = transport;
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

  @JoinColumn(name = "transport_id")
  @OneToOne
  public Transport getTransport() {
    return transport;
  }

  public void setTransport(Transport transport) {
    this.transport = transport;
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
