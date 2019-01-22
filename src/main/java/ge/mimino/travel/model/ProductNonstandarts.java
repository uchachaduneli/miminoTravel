package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_nonstandarts", schema = "mimino")
public class ProductNonstandarts {
  private Integer id;
  private NonstandartService nonstandartService;
  private Integer requestId;
  private Integer day;

  public ProductNonstandarts() {
  }

  public ProductNonstandarts(NonstandartService nonstandartService, Integer requestId, Integer day) {
    this.nonstandartService = nonstandartService;
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

  @JoinColumn(name = "nonstandart_service_id")
  @OneToOne
  public NonstandartService getNonstandartService() {
    return nonstandartService;
  }

  public void setNonstandartService(NonstandartService nonstandartService) {
    this.nonstandartService = nonstandartService;
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
