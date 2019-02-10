package ge.mimino.travel.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guide")
public class Guide {
  private Integer id;
  private String name;
  private Double trackingPrice;
  private Integer type;
  private Language language;
  private Set<GuidePrices> prices = new HashSet<GuidePrices>(0);

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
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "tracking_price")
  public Double getTrackingPrice() {
    return trackingPrice;
  }

  public void setTrackingPrice(Double trackingPrice) {
    this.trackingPrice = trackingPrice;
  }

  @Basic
  @Column(name = "type")
  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "guide", orphanRemoval = true, cascade = CascadeType.ALL)
  @OrderBy("id")
  public Set<GuidePrices> getPrices() {
    return prices;
  }

  public void setPrices(Set<GuidePrices> prices) {
    this.prices = prices;
  }

  @JoinColumn(name = "language_id")
  @OneToOne
  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }
}
