package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Product {
  private Integer id;
  private Integer requestId;
  private String introText;
  private String introImg;
  private Timestamp createDate;

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
  @Column(name = "intro_text")
  public String getIntroText() {
    return introText;
  }

  public void setIntroText(String introText) {
    this.introText = introText;
  }

  @Basic
  @Column(name = "intro_img")
  public String getIntroImg() {
    return introImg;
  }

  public void setIntroImg(String introImg) {
    this.introImg = introImg;
  }

  @Basic
  @Column(name = "create_date", updatable = false, insertable = false)
  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id) &&
            Objects.equals(requestId, product.requestId) &&
            Objects.equals(introText, product.introText) &&
            Objects.equals(introImg, product.introImg) &&
            Objects.equals(createDate, product.createDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, requestId, introText, introImg, createDate);
  }
}
