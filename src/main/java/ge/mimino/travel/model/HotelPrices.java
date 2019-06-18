package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "hotel_prices", schema = "mimino")
public class HotelPrices {
    private Integer id;
    private String date;
    private Hotel hotel;
    private Double usd;
    private Double eur;
    private Double gel;
    private Timestamp createDate;

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
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
    @Column(name = "usd")
    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    @Basic
    @Column(name = "eur")
    public Double getEur() {
        return eur;
    }

    public void setEur(Double eur) {
        this.eur = eur;
    }

    @Basic
    @Column(name = "gel")
    public Double getGel() {
        return gel;
    }

    public void setGel(Double gel) {
        this.gel = gel;
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
