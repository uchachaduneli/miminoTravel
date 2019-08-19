package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "hotel_prices", schema = "mimino")
public class HotelPrices {
    private Integer id;
    private Date date;
    private Hotel hotel;
    private Timestamp createDate;
    private Double singleFit;
    private Double singleGroup;
    private Double doubleFit;
    private Double doubleGroup;
    private Double tripleFit;
    private Double tripleGroup;
    private Double singleSupplementFit;
    private Double singleSupplementGroup;
    private HotelPriceDateRanges dateRange;

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
    @Column(name = "`date`")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
    @Column(name = "create_date", nullable = false, insertable = false, updatable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "single_fit")
    public Double getSingleFit() {
        return singleFit;
    }

    public void setSingleFit(Double singleFit) {
        this.singleFit = singleFit;
    }

    @Basic
    @Column(name = "single_group")
    public Double getSingleGroup() {
        return singleGroup;
    }

    public void setSingleGroup(Double singleGroup) {
        this.singleGroup = singleGroup;
    }

    @Basic
    @Column(name = "double_fit")
    public Double getDoubleFit() {
        return doubleFit;
    }

    public void setDoubleFit(Double doubleFit) {
        this.doubleFit = doubleFit;
    }

    @Basic
    @Column(name = "double_group")
    public Double getDoubleGroup() {
        return doubleGroup;
    }

    public void setDoubleGroup(Double doubleGroup) {
        this.doubleGroup = doubleGroup;
    }

    @Basic
    @Column(name = "triple_fit")
    public Double getTripleFit() {
        return tripleFit;
    }

    public void setTripleFit(Double tripleFit) {
        this.tripleFit = tripleFit;
    }

    @Basic
    @Column(name = "triple_group")
    public Double getTripleGroup() {
        return tripleGroup;
    }

    public void setTripleGroup(Double tripleGroup) {
        this.tripleGroup = tripleGroup;
    }

    @Basic
    @Column(name = "single_supplement_fit")
    public Double getSingleSupplementFit() {
        return singleSupplementFit;
    }

    public void setSingleSupplementFit(Double singleSupplementFit) {
        this.singleSupplementFit = singleSupplementFit;
    }

    @Basic
    @Column(name = "single_supplement_group")
    public Double getSingleSupplementGroup() {
        return singleSupplementGroup;
    }

    public void setSingleSupplementGroup(Double singleSupplementGroup) {
        this.singleSupplementGroup = singleSupplementGroup;
    }

    @JoinColumn(name = "date_range_id")
    @OneToOne
    public HotelPriceDateRanges getDateRange() {
        return dateRange;
    }

    public void setDateRange(HotelPriceDateRanges dateRange) {
        this.dateRange = dateRange;
    }

}
