package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_nonstandarts", schema = "mimino")
public class ProductNonstandarts {
    private Integer id;
    private Integer nonstandartServiceId;
    private Integer requestId;
    private Integer day;

    public ProductNonstandarts() {
    }

    public ProductNonstandarts(Integer nonstandartServiceId, Integer requestId, Integer day) {
        this.nonstandartServiceId = nonstandartServiceId;
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
    @Column(name = "nonstandart_service_id")
    public Integer getNonstandartServiceId() {
        return nonstandartServiceId;
    }

    public void setNonstandartServiceId(Integer nonstandartServiceId) {
        this.nonstandartServiceId = nonstandartServiceId;
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

        ProductNonstandarts that = (ProductNonstandarts) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nonstandartServiceId != null ? !nonstandartServiceId.equals(that.nonstandartServiceId) : that.nonstandartServiceId != null)
            return false;
        if (requestId != null ? !requestId.equals(that.requestId) : that.requestId != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nonstandartServiceId != null ? nonstandartServiceId.hashCode() : 0);
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        return result;
    }
}
