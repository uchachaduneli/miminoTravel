package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "case_country", schema = "university")
public class CaseCountry {
    private Integer id;
    private Integer caseId;
    private Country country;
    private Integer daysCount;
    private String note;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "case_id", nullable = false)
    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    @JoinColumn(name = "country_id")
    @OneToOne
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country countryId) {
        this.country = country;
    }

    @Basic
    @Column(name = "days_count", nullable = false)
    public Integer getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(Integer daysCount) {
        this.daysCount = daysCount;
    }

    @Basic
    @Column(name = "note", nullable = false, length = 200)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
