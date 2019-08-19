package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "hotel_price_date_ranges", schema = "mimino")
public class HotelPriceDateRanges {
    private Integer id;
    private Date from;
    private Date to;

    public HotelPriceDateRanges() {
    }

    public HotelPriceDateRanges(Date from, Date to) {
        this.from = from;
        this.to = to;
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
    @Column(name = "`from`")
    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    @Basic
    @Column(name = "`to`")
    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelPriceDateRanges that = (HotelPriceDateRanges) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to);
    }
}
