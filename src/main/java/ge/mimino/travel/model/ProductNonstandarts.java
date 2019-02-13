package ge.mimino.travel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_nonstandarts", schema = "mimino")
public class ProductNonstandarts {
  private Integer id;
  private NonstandartService nonstandartService;
  private Integer requestId;
  private Integer day;
    private Double price;

  public ProductNonstandarts() {
  }

    public ProductNonstandarts(NonstandartService nonstandartService, Integer requestId, Integer day, Double price) {
        this.nonstandartService = nonstandartService;
        this.requestId = requestId;
        this.day = day;
        this.price = price;
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

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductNonstandarts that = (ProductNonstandarts) o;
        return Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
