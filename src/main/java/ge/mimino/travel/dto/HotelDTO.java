package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateTimeSerializeSupport;
import ge.mimino.travel.model.Hotel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HotelDTO {

    private Integer id;
    private String name;
    private String description;
    private Double singlePrice;
    private Double doublePrice;
    private Double triplePrice;
    private Double singleSupply;
    private String starsCount;
    private LanguageDTO language;
    private Integer languageId;
    @JsonSerialize(using = JsonDateTimeSerializeSupport.class)
    private Timestamp createDate;


    public static HotelDTO parse(Hotel record) {
        HotelDTO dto = new HotelDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setDescription(record.getDescription());
        dto.setSinglePrice(record.getSinglePrice());
        dto.setDoublePrice(record.getDoublePrice());
        dto.setTriplePrice(record.getTriplePrice());
        dto.setSingleSupply(record.getSingleSupply());
        dto.setStarsCount(record.getStarsCount());
        dto.setLanguage(LanguageDTO.parse(record.getLanguage()));
        dto.setLanguageId(record.getLanguage().getId());
        dto.setCreateDate(record.getCreateDate());
        return dto;
    }


    public static List<HotelDTO> parseToList(List<Hotel> records) {
        ArrayList<HotelDTO> list = new ArrayList<HotelDTO>();
        for (Hotel record : records) {
            list.add(HotelDTO.parse(record));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public Double getDoublePrice() {
        return doublePrice;
    }

    public void setDoublePrice(Double doublePrice) {
        this.doublePrice = doublePrice;
    }

    public Double getTriplePrice() {
        return triplePrice;
    }

    public void setTriplePrice(Double triplePrice) {
        this.triplePrice = triplePrice;
    }

    public Double getSingleSupply() {
        return singleSupply;
    }

    public void setSingleSupply(Double singleSupply) {
        this.singleSupply = singleSupply;
    }

    public String getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(String starsCount) {
        this.starsCount = starsCount;
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
