package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "hotel")
public class Hotel {
    private Integer id;
    private String name;
    private Language language;
    private String description;
    private Double singlePrice;
    private Double doublePrice;
    private Double triplePrice;
    private Double singleSupply;
    private String starsCount;
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
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "description", nullable = true, precision = 0)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
