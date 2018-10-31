package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "tourist_count")
public class TouristCount {
  private Integer id;
  private Integer requestId;
  private Integer count;
  private String strCount;

  public TouristCount(Integer requestId, Integer count, String strCount) {
    this.requestId = requestId;
    this.count = count;
    this.strCount = strCount;
  }

  public TouristCount() {
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
  @Column(name = "str_count")
  public String getStrCount() {
    return strCount;
  }

  public void setStrCount(String strCount) {
    this.strCount = strCount;
  }

}
