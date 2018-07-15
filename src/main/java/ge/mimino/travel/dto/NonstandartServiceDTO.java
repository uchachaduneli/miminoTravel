package ge.mimino.travel.dto;

import ge.mimino.travel.model.NonstandartService;

import java.util.ArrayList;
import java.util.List;

public class NonstandartServiceDTO {

    private Integer id;
    private String name;
    private String description;
    private Double price;
    private LanguageDTO language;
    private Integer languageId;


    public static NonstandartServiceDTO parse(NonstandartService record) {
        NonstandartServiceDTO dto = new NonstandartServiceDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setDescription(record.getDescription());
        dto.setPrice(record.getPrice());
        dto.setLanguage(LanguageDTO.parse(record.getLanguage()));
        dto.setLanguageId(record.getLanguage().getId());
        return dto;
    }


    public static List<NonstandartServiceDTO> parseToList(List<NonstandartService> records) {
        ArrayList<NonstandartServiceDTO> list = new ArrayList<NonstandartServiceDTO>();
        for (NonstandartService record : records) {
            list.add(NonstandartServiceDTO.parse(record));
        }
        return list;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
