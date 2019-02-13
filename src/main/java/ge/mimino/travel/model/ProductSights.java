package ge.mimino.travel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_sights", schema = "mimino")
public class ProductSights {
    private Integer id;
    private GeoObject sight;
    private Integer requestId;
    private Integer day;
    private Integer photoOrVisit;
    private Double price;

    public ProductSights() {
    }

    public ProductSights(GeoObject sight, Integer requestId, Integer day, Integer photoOrVisit, Double price) {
        this.sight = sight;
        this.requestId = requestId;
        this.day = day;
        this.photoOrVisit = photoOrVisit;
        this.price = price;
    }

    public ProductSights(GeoObject sight, Integer requestId, Integer day, Integer photoOrVisit) {
        this.sight = sight;
        this.requestId = requestId;
        this.day = day;
        this.photoOrVisit = photoOrVisit;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JoinColumn(name = "sight_id")
    @OneToOne
    public GeoObject getSight() {
        return sight;
    }

    public void setSight(GeoObject sight) {
        this.sight = sight;
    }

    @Basic
    @Column(name = "request_id")
    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @Basic
    @Column(name = "day")
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Basic
    @Column(name = "photo_or_visit")
    public Integer getPhotoOrVisit() {
        return photoOrVisit;
    }

    public void setPhotoOrVisit(Integer photoOrVisit) {
        this.photoOrVisit = photoOrVisit;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSights that = (ProductSights) o;
        return Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
