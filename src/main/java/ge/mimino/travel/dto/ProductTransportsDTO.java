package ge.mimino.travel.dto;

import ge.mimino.travel.model.ProductTransports;

import java.util.ArrayList;
import java.util.List;

public class ProductTransportsDTO {

  private Integer id;
  private Integer transportId;
  private Integer requestId;
  private Integer count;
  private Integer touristCount;


  public static ProductTransportsDTO parse(ProductTransports record) {
    ProductTransportsDTO dto = new ProductTransportsDTO();
    dto.setId(record.getId());
    dto.setTransportId(record.getTransportId());
    dto.setRequestId(record.getRequestId());
    dto.setCount(record.getCount());
    dto.setTouristCount(record.getTouristCount());
    return dto;
  }


  public static List<ProductTransportsDTO> parseToList(List<ProductTransports> records) {
    ArrayList<ProductTransportsDTO> list = new ArrayList<ProductTransportsDTO>();
    for (ProductTransports record : records) {
      list.add(ProductTransportsDTO.parse(record));
    }
    return list;
  }

  public Integer getTouristCount() {
    return touristCount;
  }

  public void setTouristCount(Integer touristCount) {
    this.touristCount = touristCount;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getTransportId() {
    return transportId;
  }

  public void setTransportId(Integer transportId) {
    this.transportId = transportId;
  }

  public Integer getRequestId() {
    return requestId;
  }

  public void setRequestId(Integer requestId) {
    this.requestId = requestId;
  }
}
