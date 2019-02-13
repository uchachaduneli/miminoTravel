package ge.mimino.travel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_hotels", schema = "mimino")
public class ProductHotels {
    private Integer id;
    private Hotel hotel;
    private Integer requestId;
    private Integer day;
    private Integer groupId;
    private Double price;

    public ProductHotels() {
    }

    public ProductHotels(Hotel hotel, Integer requestId, Integer day, Integer groupId) {
        this.hotel = hotel;
        this.requestId = requestId;
        this.day = day;
        this.groupId = groupId;
    }

    public ProductHotels(Hotel hotel, Integer requestId, Integer day, Integer groupId, Double price) {
        this.hotel = hotel;
        this.requestId = requestId;
        this.day = day;
        this.groupId = groupId;
        this.price = price;
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

    @JoinColumn(name = "hotel_id")
    @OneToOne
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
        ProductHotels that = (ProductHotels) o;
        return Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
