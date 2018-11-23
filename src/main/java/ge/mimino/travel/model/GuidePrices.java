package ge.mimino.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "guide_prices")
public class GuidePrices {
  private Integer id;
  private Guide guide;
  private Integer from;
  private Integer to;
  private Double amount;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "guide_id", nullable = false)
  @JsonIgnore
  public Guide getGuide() {
    return guide;
  }

  public void setGuide(Guide guide) {
    this.guide = guide;
  }

  @Basic
  @Column(name = "from_count")
  public Integer getFrom() {
    return from;
  }

  public void setFrom(Integer from) {
    this.from = from;
  }

  @Basic
  @Column(name = "to_count")
  public Integer getTo() {
    return to;
  }

  public void setTo(Integer to) {
    this.to = to;
  }

  @Basic
  @Column(name = "amount")
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

}
