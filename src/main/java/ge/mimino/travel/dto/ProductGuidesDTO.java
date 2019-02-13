package ge.mimino.travel.dto;

import ge.mimino.travel.model.ProductGuides;

import java.util.ArrayList;
import java.util.List;

public class ProductGuidesDTO {

    private Integer id;
    private GuideDTO guide;
    private Integer requestId;
    private Double price;
    private String days;


    public static ProductGuidesDTO parse(ProductGuides record) {
        ProductGuidesDTO dto = new ProductGuidesDTO();
        dto.setId(record.getId());
        dto.setGuide(GuideDTO.parse(record.getGuide()));
        dto.setRequestId(record.getRequestId());
        dto.setPrice(record.getPrice());
        dto.setDays(record.getDays());
        return dto;
    }


    public static List<ProductGuidesDTO> parseToList(List<ProductGuides> records) {
        ArrayList<ProductGuidesDTO> list = new ArrayList<ProductGuidesDTO>();
        for (ProductGuides record : records) {
            list.add(ProductGuidesDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GuideDTO getGuide() {
        return guide;
    }

    public void setGuide(GuideDTO guide) {
        this.guide = guide;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
