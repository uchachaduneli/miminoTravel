package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_guides", schema = "mimino")
public class ProductGuides {
    private Integer id;
    private Guide guide;
    private Integer requestId;
    private Double price;
    private String days;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JoinColumn(name = "guide_id")
    @OneToOne
    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
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
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "days")
    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

}
