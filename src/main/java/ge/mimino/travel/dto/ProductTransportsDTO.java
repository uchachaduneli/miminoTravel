package ge.mimino.travel.dto;

import ge.mimino.travel.model.ProductTransports;

import java.util.ArrayList;
import java.util.List;

public class ProductTransportsDTO {

    private Integer id;
    private Integer transportId;
    private Integer requestId;
    private Integer day;


    public static ProductTransportsDTO parse(ProductTransports record) {
        ProductTransportsDTO dto = new ProductTransportsDTO();
        dto.setId(record.getId());
        dto.setTransportId(record.getTransportId());
        dto.setRequestId(record.getRequestId());
        dto.setDay(record.getDay());
        return dto;
    }


    public static List<ProductTransportsDTO> parseToList(List<ProductTransports> records) {
        ArrayList<ProductTransportsDTO> list = new ArrayList<ProductTransportsDTO>();
        for (ProductTransports record : records) {
            list.add(ProductTransportsDTO.parse(record));
        }
        return list;
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

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
