package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "transport")
public class Transport {
    private Integer id;
    private Integer seatsCount;
    private Fuel fuel;
    private Double fuelConsumption;
    private Double price;
    private Integer fuelId;
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
    @Column(name = "seats_count", nullable = false)
    public Integer getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(Integer seatsCount) {
        this.seatsCount = seatsCount;
    }

    @JoinColumn(name = "fuel_id")
    @OneToOne
    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    @Basic
    @Column(name = "fuel_consumption", nullable = false, precision = 0)
    public Double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "fuel_id")
    public Integer getFuelId() {
        return fuelId;
    }

    public void setFuelId(Integer fuelId) {
        this.fuelId = fuelId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transport transport = (Transport) o;

        if (id != null ? !id.equals(transport.id) : transport.id != null) return false;
        if (seatsCount != null ? !seatsCount.equals(transport.seatsCount) : transport.seatsCount != null) return false;
        if (fuelConsumption != null ? !fuelConsumption.equals(transport.fuelConsumption) : transport.fuelConsumption != null)
            return false;
        if (price != null ? !price.equals(transport.price) : transport.price != null) return false;
        if (fuelId != null ? !fuelId.equals(transport.fuelId) : transport.fuelId != null) return false;
        if (nameEn != null ? !nameEn.equals(transport.nameEn) : transport.nameEn != null) return false;
        if (nameGe != null ? !nameGe.equals(transport.nameGe) : transport.nameGe != null) return false;
        if (nameFr != null ? !nameFr.equals(transport.nameFr) : transport.nameFr != null) return false;
        if (nameIt != null ? !nameIt.equals(transport.nameIt) : transport.nameIt != null) return false;
        if (nameSp != null ? !nameSp.equals(transport.nameSp) : transport.nameSp != null) return false;
        if (namePo != null ? !namePo.equals(transport.namePo) : transport.namePo != null) return false;
        if (nameRu != null ? !nameRu.equals(transport.nameRu) : transport.nameRu != null) return false;
        if (descriptionEn != null ? !descriptionEn.equals(transport.descriptionEn) : transport.descriptionEn != null)
            return false;
        if (descriptionGe != null ? !descriptionGe.equals(transport.descriptionGe) : transport.descriptionGe != null)
            return false;
        if (descriptionFr != null ? !descriptionFr.equals(transport.descriptionFr) : transport.descriptionFr != null)
            return false;
        if (descriptionIt != null ? !descriptionIt.equals(transport.descriptionIt) : transport.descriptionIt != null)
            return false;
        if (descriptionSp != null ? !descriptionSp.equals(transport.descriptionSp) : transport.descriptionSp != null)
            return false;
        if (descriptionPo != null ? !descriptionPo.equals(transport.descriptionPo) : transport.descriptionPo != null)
            return false;
        if (descriptionRu != null ? !descriptionRu.equals(transport.descriptionRu) : transport.descriptionRu != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (seatsCount != null ? seatsCount.hashCode() : 0);
        result = 31 * result + (fuelId != null ? fuelId.hashCode() : 0);
        result = 31 * result + (fuelConsumption != null ? fuelConsumption.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (nameGe != null ? nameGe.hashCode() : 0);
        result = 31 * result + (nameFr != null ? nameFr.hashCode() : 0);
        result = 31 * result + (nameIt != null ? nameIt.hashCode() : 0);
        result = 31 * result + (nameSp != null ? nameSp.hashCode() : 0);
        result = 31 * result + (namePo != null ? namePo.hashCode() : 0);
        result = 31 * result + (nameRu != null ? nameRu.hashCode() : 0);
        result = 31 * result + (descriptionEn != null ? descriptionEn.hashCode() : 0);
        result = 31 * result + (descriptionGe != null ? descriptionGe.hashCode() : 0);
        result = 31 * result + (descriptionFr != null ? descriptionFr.hashCode() : 0);
        result = 31 * result + (descriptionIt != null ? descriptionIt.hashCode() : 0);
        result = 31 * result + (descriptionSp != null ? descriptionSp.hashCode() : 0);
        result = 31 * result + (descriptionPo != null ? descriptionPo.hashCode() : 0);
        result = 31 * result + (descriptionRu != null ? descriptionRu.hashCode() : 0);
        return result;
    }
}
