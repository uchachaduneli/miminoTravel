package ge.mimino.travel.dto;

import ge.mimino.travel.model.ProductNonstandarts;

import java.util.ArrayList;
import java.util.List;

public class ProductNonstandartsDTO {

    private Integer id;
    private Integer nonstandartServiceId;
    private NonstandartServiceDTO nonstandartService;
    private Integer requestId;
    private Integer day;


    public static ProductNonstandartsDTO parse(ProductNonstandarts record) {
        ProductNonstandartsDTO dto = new ProductNonstandartsDTO();
        dto.setId(record.getId());
        dto.setNonstandartServiceId(record.getNonstandartService().getId());
        dto.setNonstandartService(NonstandartServiceDTO.parse(record.getNonstandartService()));
        dto.setRequestId(record.getRequestId());
        dto.setDay(record.getDay());
        return dto;
    }


    public static List<ProductNonstandartsDTO> parseToList(List<ProductNonstandarts> records) {
        ArrayList<ProductNonstandartsDTO> list = new ArrayList<ProductNonstandartsDTO>();
        for (ProductNonstandarts record : records) {
            list.add(ProductNonstandartsDTO.parse(record));
        }
        return list;
    }

    public NonstandartServiceDTO getNonstandartService() {
        return nonstandartService;
    }

    public void setNonstandartService(NonstandartServiceDTO nonstandartService) {
        this.nonstandartService = nonstandartService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNonstandartServiceId() {
        return nonstandartServiceId;
    }

    public void setNonstandartServiceId(Integer nonstandartServiceId) {
        this.nonstandartServiceId = nonstandartServiceId;
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
