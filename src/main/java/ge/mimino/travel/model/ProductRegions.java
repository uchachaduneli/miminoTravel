package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_regions", schema = "mimino")
public class ProductRegions {
    private Integer id;
    private Integer regionId;
    private Integer requestId;
    private Integer day;

    public ProductRegions() {
    }

    public ProductRegions(Integer regionId, Integer requestId, Integer day) {
        this.regionId = regionId;
        this.requestId = requestId;
        this.day = day;
    }

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
    @Column(name = "region_id", nullable = false)
    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "request_id", nullable = false)
    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @Basic
    @Column(name = "day", nullable = false)
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
