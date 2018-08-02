package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_sights", schema = "mimino")
public class ProductSights {
    private Integer id;
    private Integer sightId;
    private Integer requestId;
    private Integer day;

    public ProductSights() {
    }

    public ProductSights(Integer sightId, Integer requestId, Integer day) {
        this.sightId = sightId;
        this.requestId = requestId;
        this.day = day;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSights that = (ProductSights) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sightId != null ? !sightId.equals(that.sightId) : that.sightId != null) return false;
        if (requestId != null ? !requestId.equals(that.requestId) : that.requestId != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sightId != null ? sightId.hashCode() : 0);
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        return result;
    }
}
