package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "request_country")
public class RequestCountry {
    private Integer id;
    private Integer requestId;
    private Country country;
    private Integer daysCount;
    private String note;

    public RequestCountry(Integer requestId, Country country, Integer daysCount, String note) {
        this.requestId = requestId;
        this.country = country;
        this.daysCount = daysCount;
        this.note = note;
    }

    public RequestCountry() {
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

    @JoinColumn(name = "country_id")
    @OneToOne
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Basic
    @Column(name = "days_count")
    public Integer getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(Integer daysCount) {
        this.daysCount = daysCount;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
