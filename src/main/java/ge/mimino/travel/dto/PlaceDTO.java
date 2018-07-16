package ge.mimino.travel.dto;

import ge.mimino.travel.model.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceDTO {

    private Integer id;
    private String nameEn;
    private String nameGe;
    private String nameFr;
    private String nameIt;
    private String nameSp;
    private String namePo;
    private String nameRu;
    private String descriptionEn;
    private String descriptionGe;
    private String descriptionFr;
    private String descriptionIt;
    private String descriptionSp;
    private String descriptionPo;
    private String descriptionRu;


    public static PlaceDTO parse(Place record) {
        PlaceDTO dto = new PlaceDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setDescription(record.getDescription());
        dto.setLanguage(LanguageDTO.parse(record.getLanguage()));
        dto.setLanguageId(record.getLanguage().getId());
        return dto;
    }


    public static List<PlaceDTO> parseToList(List<Place> records) {
        ArrayList<PlaceDTO> list = new ArrayList<PlaceDTO>();
        for (Place record : records) {
            list.add(PlaceDTO.parse(record));
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
}
