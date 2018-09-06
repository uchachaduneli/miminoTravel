package ge.mimino.travel.dto;

import ge.mimino.travel.model.ProductSights;

import java.util.ArrayList;
import java.util.List;

public class ProductSightsDTO {

    private Integer id;
    private Integer sightId;
    private Integer requestId;
    private Integer day;
    private Integer photoOrVisit;


    public static ProductSightsDTO parse(ProductSights record) {
        ProductSightsDTO dto = new ProductSightsDTO();
        dto.setId(record.getId());
        dto.setSightId(record.getSightId());
        dto.setRequestId(record.getRequestId());
        dto.setDay(record.getDay());
        dto.setPhotoOrVisit(record.getPhotoOrVisit());
        return dto;
    }


    public static List<ProductSightsDTO> parseToList(List<ProductSights> records) {
        ArrayList<ProductSightsDTO> list = new ArrayList<ProductSightsDTO>();
        for (ProductSights record : records) {
            list.add(ProductSightsDTO.parse(record));
        }
        return list;
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
