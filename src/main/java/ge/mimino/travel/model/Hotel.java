package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "hotel")
public class Hotel {
    private Integer id;
    private Double singlePrice;
    private Double doublePrice;
    private Double triplePrice;
    private Double singleSupply;
    private String starsCount;
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

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "single_price", nullable = true, precision = 0)
    public Double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Double singlePrice) {
        this.singlePrice = singlePrice;
    }

    @Basic
    @Column(name = "double_price", nullable = true, precision = 0)
    public Double getDoublePrice() {
        return doublePrice;
    }

    public void setDoublePrice(Double doublePrice) {
        this.doublePrice = doublePrice;
    }

    @Basic
    @Column(name = "triple_price", nullable = true, precision = 0)
    public Double getTriplePrice() {
        return triplePrice;
    }

    public void setTriplePrice(Double triplePrice) {
        this.triplePrice = triplePrice;
    }

    @Basic
    @Column(name = "single_supply", nullable = true, precision = 0)
    public Double getSingleSupply() {
        return singleSupply;
    }

    public void setSingleSupply(Double singleSupply) {
        this.singleSupply = singleSupply;
    }

    @Basic
    @Column(name = "stars_count", nullable = true, length = 50)
    public String getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(String starsCount) {
        this.starsCount = starsCount;
    }

    @Basic
    @Column(name = "create_date", nullable = false, insertable = false, updatable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "name_en")
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Basic
    @Column(name = "name_ge")
    public String getNameGe() {
        return nameGe;
    }

    public void setNameGe(String nameGe) {
        this.nameGe = nameGe;
    }

    @Basic
    @Column(name = "name_fr")
    public String getNameFr() {
        return nameFr;
    }

    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

    @Basic
    @Column(name = "name_it")
    public String getNameIt() {
        return nameIt;
    }

    public void setNameIt(String nameIt) {
        this.nameIt = nameIt;
    }

    @Basic
    @Column(name = "name_sp")
    public String getNameSp() {
        return nameSp;
    }

    public void setNameSp(String nameSp) {
        this.nameSp = nameSp;
    }

    @Basic
    @Column(name = "name_po")
    public String getNamePo() {
        return namePo;
    }

    public void setNamePo(String namePo) {
        this.namePo = namePo;
    }

    @Basic
    @Column(name = "name_ru")
    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @Basic
    @Column(name = "description_en")
    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    @Basic
    @Column(name = "description_ge")
    public String getDescriptionGe() {
        return descriptionGe;
    }

    public void setDescriptionGe(String descriptionGe) {
        this.descriptionGe = descriptionGe;
    }

    @Basic
    @Column(name = "description_fr")
    public String getDescriptionFr() {
        return descriptionFr;
    }

    public void setDescriptionFr(String descriptionFr) {
        this.descriptionFr = descriptionFr;
    }

    @Basic
    @Column(name = "description_it")
    public String getDescriptionIt() {
        return descriptionIt;
    }

    public void setDescriptionIt(String descriptionIt) {
        this.descriptionIt = descriptionIt;
    }

    @Basic
    @Column(name = "description_sp")
    public String getDescriptionSp() {
        return descriptionSp;
    }

    public void setDescriptionSp(String descriptionSp) {
        this.descriptionSp = descriptionSp;
    }

    @Basic
    @Column(name = "description_po")
    public String getDescriptionPo() {
        return descriptionPo;
    }

    public void setDescriptionPo(String descriptionPo) {
        this.descriptionPo = descriptionPo;
    }

    @Basic
    @Column(name = "description_ru")
    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

}
