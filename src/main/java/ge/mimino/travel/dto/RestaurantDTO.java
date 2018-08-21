package ge.mimino.travel.dto;

import ge.mimino.travel.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDTO {

    private Integer id;
    private PlaceDTO place;
    private Integer placeId;
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


    public static RestaurantDTO parse(Restaurant record) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(record.getId());
        dto.setNameEn(record.getNameEn());
        dto.setNameGe(record.getNameGe());
        dto.setNameFr(record.getNameFr());
        dto.setNameIt(record.getNameIt());
        dto.setNameSp(record.getNameSp());
        dto.setNamePo(record.getNamePo());
        dto.setNameRu(record.getNameRu());
        dto.setDescriptionEn(record.getDescriptionEn());
        dto.setDescriptionGe(record.getDescriptionGe());
        dto.setDescriptionFr(record.getDescriptionFr());
        dto.setDescriptionIt(record.getDescriptionIt());
        dto.setDescriptionSp(record.getDescriptionSp());
        dto.setDescriptionPo(record.getDescriptionPo());
        dto.setDescriptionRu(record.getDescriptionRu());
        dto.setPlace(PlaceDTO.parse(record.getPlace()));
        dto.setPlaceId(record.getPlace().getId());
        return dto;
    }


    public static List<RestaurantDTO> parseToList(List<Restaurant> records) {
        ArrayList<RestaurantDTO> list = new ArrayList<RestaurantDTO>();
        for (Restaurant record : records) {
            list.add(RestaurantDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlaceDTO getPlace() {
        return place;
    }

    public void setPlace(PlaceDTO place) {
        this.place = place;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameGe() {
        return nameGe;
    }

    public void setNameGe(String nameGe) {
        this.nameGe = nameGe;
    }

    public String getNameFr() {
        return nameFr;
    }

    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

    public String getNameIt() {
        return nameIt;
    }

    public void setNameIt(String nameIt) {
        this.nameIt = nameIt;
    }

    public String getNameSp() {
        return nameSp;
    }

    public void setNameSp(String nameSp) {
        this.nameSp = nameSp;
    }

    public String getNamePo() {
        return namePo;
    }

    public void setNamePo(String namePo) {
        this.namePo = namePo;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionGe() {
        return descriptionGe;
    }

    public void setDescriptionGe(String descriptionGe) {
        this.descriptionGe = descriptionGe;
    }

    public String getDescriptionFr() {
        return descriptionFr;
    }

    public void setDescriptionFr(String descriptionFr) {
        this.descriptionFr = descriptionFr;
    }

    public String getDescriptionIt() {
        return descriptionIt;
    }

    public void setDescriptionIt(String descriptionIt) {
        this.descriptionIt = descriptionIt;
    }

    public String getDescriptionSp() {
        return descriptionSp;
    }

    public void setDescriptionSp(String descriptionSp) {
        this.descriptionSp = descriptionSp;
    }

    public String getDescriptionPo() {
        return descriptionPo;
    }

    public void setDescriptionPo(String descriptionPo) {
        this.descriptionPo = descriptionPo;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }
}
