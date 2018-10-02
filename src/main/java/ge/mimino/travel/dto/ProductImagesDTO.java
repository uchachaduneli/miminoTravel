package ge.mimino.travel.dto;

import ge.mimino.travel.model.ProductImages;

import java.util.ArrayList;
import java.util.List;

public class ProductImagesDTO {

  private Integer id;
  private String name;


  public static ProductImagesDTO parse(ProductImages record) {
    ProductImagesDTO dto = new ProductImagesDTO();
    dto.setId(record.getId());
    dto.setName(record.getName());
    return dto;
  }


  public static List<ProductImagesDTO> parseToList(List<ProductImages> records) {
    ArrayList<ProductImagesDTO> list = new ArrayList<ProductImagesDTO>();
    for (ProductImages record : records) {
      list.add(ProductImagesDTO.parse(record));
    }
    return list;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
