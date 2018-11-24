package ge.mimino.travel.dto;

import ge.mimino.travel.model.NonstandartService;

import java.util.ArrayList;
import java.util.List;

public class NonstandartServiceDTO {

    private Integer id;
    private Double price;

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
    private Integer daily;
    private Integer individual;
    private Integer multy;


    public static NonstandartServiceDTO parse(NonstandartService record) {
        NonstandartServiceDTO dto = new NonstandartServiceDTO();
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
        dto.setPrice(record.getPrice());
        dto.setMulty(record.getMulty());
        dto.setIndividual(record.getIndividual());
        dto.setDaily(record.getDaily());
        return dto;
    }


    public static List<NonstandartServiceDTO> parseToList(List<NonstandartService> records) {
        ArrayList<NonstandartServiceDTO> list = new ArrayList<NonstandartServiceDTO>();
        for (NonstandartService record : records) {
            list.add(NonstandartServiceDTO.parse(record));
        }
        return list;
    }

    public Integer getDaily() {
        return daily;
    }

    public void setDaily(Integer daily) {
        this.daily = daily;
    }

    public Integer getIndividual() {
        return individual;
    }

    public void setIndividual(Integer individual) {
        this.individual = individual;
    }

    public Integer getMulty() {
        return multy;
    }

    public void setMulty(Integer multy) {
        this.multy = multy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
