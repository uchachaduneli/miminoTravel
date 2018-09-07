package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_sights", schema = "mimino")
public class ProductSights {
    private Integer id;
    private Integer sightId;
    private Integer requestId;
    private Integer day;
    private Integer photoOrVisit;

    public ProductSights() {
    }

    public ProductSights(Integer sightId, Integer requestId, Integer day, Integer photoOrVisit) {
        this.sightId = sightId;
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

    @Basic
    @Column(name = "sight_id")
    public Integer getSightId() {
        return sightId;
    }

    public void setSightId(Integer sightId) {
        this.sightId = sightId;
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
}
