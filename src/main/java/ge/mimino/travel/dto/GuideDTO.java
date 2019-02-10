package ge.mimino.travel.dto;

import ge.mimino.travel.model.Guide;
import ge.mimino.travel.model.GuidePrices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuideDTO {

    private Integer id;
    private String name;
    private Double trackingPrice;
    private Integer type; //1 guide / 2 driver-guide
    private Integer languageId;
    private LanguageDTO language;
    private Set<GuidePrices> prices = new HashSet<GuidePrices>(0);


    public static GuideDTO parse(Guide record) {
        GuideDTO dto = new GuideDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setTrackingPrice(record.getTrackingPrice());
        dto.setType(record.getType());
        dto.setLanguage(LanguageDTO.parse(record.getLanguage()));
        dto.setLanguageId(record.getLanguage().getId());
        return dto;
    }


    public static List<GuideDTO> parseToList(List<Guide> records) {
        ArrayList<GuideDTO> list = new ArrayList<GuideDTO>();
        for (Guide record : records) {
            list.add(GuideDTO.parse(record));
        }
        return list;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
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

    public Double getTrackingPrice() {
        return trackingPrice;
    }

    public void setTrackingPrice(Double trackingPrice) {
        this.trackingPrice = trackingPrice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public Set<GuidePrices> getPrices() {
        return prices;
    }

    public void setPrices(Set<GuidePrices> prices) {
        this.prices = prices;
    }
}
