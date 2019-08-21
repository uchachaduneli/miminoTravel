package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "tourist_count", schema = "mimino")
public class TouristCount {
    private Integer id;
    private Integer requestId;
    private Integer count;
    private Integer plusCount;
    private String plusCountStr;

    public TouristCount() {
    }

    public TouristCount(Integer requestId, Integer count, Integer plusCount, String plusCountStr) {
        this.requestId = requestId;
        this.count = count;
        this.plusCount = plusCount;
        this.plusCountStr = plusCountStr;
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
    public Integer getPlusCount() {
        return plusCount;
    }

    public void setPlusCount(Integer plusCount) {
        this.plusCount = plusCount;
    }


    @Basic
    @Column(name = "plus_count_str")
    public String getPlusCountStr() {
        return plusCountStr;
    }

    public void setPlusCountStr(String plusCountStr) {
        this.plusCountStr = plusCountStr;
    }
}
