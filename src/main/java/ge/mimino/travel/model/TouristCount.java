package ge.mimino.travel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tourist_count", schema = "mimino")
public class TouristCount {
    private Integer id;
    private Integer requestId;
    private Integer count;
    private String plusCount;

    public TouristCount() {
    }

    public TouristCount(Integer requestId, Integer count, String plusCount) {
        this.requestId = requestId;
        this.count = count;
        this.plusCount = plusCount;
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
    @Column(name = "request_id")
    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "plus_count")
    public String getPlusCount() {
        return plusCount;
    }

    public void setPlusCount(String plusCount) {
        this.plusCount = plusCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TouristCount that = (TouristCount) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(count, that.count) &&
                Objects.equals(plusCount, that.plusCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestId, count, plusCount);
    }
}
