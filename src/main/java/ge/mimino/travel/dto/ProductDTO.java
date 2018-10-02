package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateSerializeSupport;
import ge.mimino.travel.model.Product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO {

  private Integer id;
  private Integer requestId;
  private String introText;
  private String introImg;
  @JsonSerialize(using = JsonDateSerializeSupport.class)
  private Timestamp createDate;


  public static ProductDTO parse(Product record) {
    ProductDTO dto = new ProductDTO();
    dto.setId(record.getId());
    dto.setRequestId(record.getRequestId());
    dto.setIntroText(record.getIntroText());
    dto.setIntroImg(record.getIntroImg());
    dto.setCreateDate(record.getCreateDate());
    return dto;
  }


  public static List<ProductDTO> parseToList(List<Product> records) {
    ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
    for (Product record : records) {
      list.add(ProductDTO.parse(record));
    }
    return list;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getRequestId() {
    return requestId;
  }

  public void setRequestId(Integer requestId) {
    this.requestId = requestId;
  }

  public String getIntroText() {
    return introText;
  }

  public void setIntroText(String introText) {
    this.introText = introText;
  }

  public String getIntroImg() {
    return introImg;
  }

  public void setIntroImg(String introImg) {
    this.introImg = introImg;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }
}
