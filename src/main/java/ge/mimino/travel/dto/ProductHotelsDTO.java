package ge.mimino.travel.dto;

import ge.mimino.travel.model.ProductHotels;

import java.util.ArrayList;
import java.util.List;

public class ProductHotelsDTO {

  private Integer id;
  private Integer hotelId;
  private HotelDTO hotel;
  private Integer requestId;
  private Integer day;
  private Integer groupId;
    private Double price;


  public static ProductHotelsDTO parse(ProductHotels record) {
    ProductHotelsDTO dto = new ProductHotelsDTO();
    dto.setId(record.getId());
    dto.setHotelId(record.getHotel().getId());
    dto.setHotel(HotelDTO.parse(record.getHotel()));
    dto.setRequestId(record.getRequestId());
    dto.setDay(record.getDay());
    dto.setGroupId(record.getGroupId());
      dto.setPrice(record.getPrice());
    return dto;
  }


  public static List<ProductHotelsDTO> parseToList(List<ProductHotels> records) {
    ArrayList<ProductHotelsDTO> list = new ArrayList<ProductHotelsDTO>();
    for (ProductHotels record : records) {
      list.add(ProductHotelsDTO.parse(record));
    }
    return list;
  }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

  public HotelDTO getHotel() {
    return hotel;
  }

  public void setHotel(HotelDTO hotel) {
    this.hotel = hotel;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getHotelId() {
    return hotelId;
  }

  public void setHotelId(Integer hotelId) {
    this.hotelId = hotelId;
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
