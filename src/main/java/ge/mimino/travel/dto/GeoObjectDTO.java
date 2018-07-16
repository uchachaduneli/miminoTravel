package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateTimeSerializeSupport;
import ge.mimino.travel.model.GeoObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GeoObjectDTO {

    private Integer id;
    private Double personPrice;
    private GeoObjectTypesDTO type;
    private Integer typeId;
    @JsonSerialize(using = JsonDateTimeSerializeSupport.class)
    private Timestamp createDate;

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


    public static GeoObjectDTO parse(GeoObject record) {
        GeoObjectDTO dto = new GeoObjectDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setDescription(record.getDescription());
        dto.setLanguage(LanguageDTO.parse(record.getLanguage()));
        dto.setLanguageId(record.getLanguage().getId());
        dto.setPersonPrice(record.getPersonPrice());
        dto.setType(GeoObjectTypesDTO.parse(record.getType()));
        dto.setTypeId(record.getType().getId());
        dto.setCreateDate(record.getCreateDate());
        return dto;
    }


    public static List<GeoObjectDTO> parseToList(List<GeoObject> records) {
        ArrayList<GeoObjectDTO> list = new ArrayList<GeoObjectDTO>();
        for (GeoObject record : records) {
            list.add(GeoObjectDTO.parse(record));
        }
        return list;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public Double getPersonPrice() {
        return personPrice;
    }

    public void setPersonPrice(Double personPrice) {
        this.personPrice = personPrice;
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeoObjectTypesDTO getType() {
        return type;
    }

    public void setType(GeoObjectTypesDTO type) {
        this.type = type;
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
