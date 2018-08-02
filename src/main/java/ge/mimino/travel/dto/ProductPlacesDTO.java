package ge.mimino.travel.dto;

import ge.mimino.travel.model.ProductPlaces;

import java.util.ArrayList;
import java.util.List;

public class ProductPlacesDTO {

    private Integer id;
    private Integer placeId;
    private Integer requestId;
    private Integer day;


    public static ProductPlacesDTO parse(ProductPlaces record) {
        ProductPlacesDTO dto = new ProductPlacesDTO();
        dto.setId(record.getId());
        dto.setPlaceId(record.getPlaceId());
        dto.setRequestId(record.getRequestId());
        dto.setDay(record.getDay());
        return dto;
    }


    public static List<ProductPlacesDTO> parseToList(List<ProductPlaces> records) {
        ArrayList<ProductPlacesDTO> list = new ArrayList<ProductPlacesDTO>();
        for (ProductPlaces record : records) {
            list.add(ProductPlacesDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
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
