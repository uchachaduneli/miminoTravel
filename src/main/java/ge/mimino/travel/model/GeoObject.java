package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "geo_object")
public class GeoObject {
    private Integer id;
    private String name;
    private Double personPrice;
    private Language language;
    private String description;
    private GeoObjectTypes type;
    private Timestamp createDate;

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
    @Column(name = "name", nullable = false, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "person_price", nullable = false, precision = 0)
    public Double getPersonPrice() {
        return personPrice;
    }

    public void setPersonPrice(Double personPrice) {
        this.personPrice = personPrice;
    }

    @JoinColumn(name = "language_id")
    @OneToOne
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JoinColumn(name = "type_id")
    @OneToOne
    public GeoObjectTypes getType() {
        return type;
    }

    public void setType(GeoObjectTypes type) {
        this.type = type;
    }

    @Basic
    @Column(name = "create_date", nullable = false, insertable = false, updatable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

}
