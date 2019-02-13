package ge.mimino.travel.dto;

import ge.mimino.travel.model.ProductSights;

import java.util.ArrayList;
import java.util.List;

public class ProductSightsDTO {

  private Integer id;
  private Integer sightId;
  private GeoObjectDTO sight;
  private Integer requestId;
  private Integer day;
  private Integer photoOrVisit;
    private Double price;


  public static ProductSightsDTO parse(ProductSights record) {
    ProductSightsDTO dto = new ProductSightsDTO();
    dto.setId(record.getId());
    if (record.getSight() != null) {
      dto.setSightId(record.getSight().getId());
      dto.setSight(GeoObjectDTO.parse(record.getSight()));
    }
    dto.setRequestId(record.getRequestId());
    dto.setDay(record.getDay());
    dto.setPhotoOrVisit(record.getPhotoOrVisit());
      dto.setPrice(record.getPrice());
    return dto;
  }


  public static List<ProductSightsDTO> parseToList(List<ProductSights> records) {
    ArrayList<ProductSightsDTO> list = new ArrayList<ProductSightsDTO>();
    for (ProductSights record : records) {
      list.add(ProductSightsDTO.parse(record));
    }
    return list;
  }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

  public GeoObjectDTO getSight() {
    return sight;
  }

  public void setSight(GeoObjectDTO sight) {
    this.sight = sight;
  }

  public Integer getPhotoOrVisit() {
    return photoOrVisit;
  }

  public void setPhotoOrVisit(Integer photoOrVisit) {
    this.photoOrVisit = photoOrVisit;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getSightId() {
    return sightId;
  }

  public void setSightId(Integer sightId) {
    this.sightId = sightId;
  }

  public Integer getRequestId() {
    return requestId;
  }

  public void setRequestId(Integer requestId) {
    this.requestId = requestId;
  }

  public Integer getDay() {
    return day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }
}
