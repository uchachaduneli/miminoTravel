package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Contact {
    private Integer id;
    private String name;
    private String contactPerson;
    private String info;
    private Date lastActivity;
    private String activity;
    private Date nextActivity;
    private String phone;
    private String website;
    private Country country;
    private String city;
    private ContactRank rank;
    private String source;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "contact_person")
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "last_activity")
    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    @Basic
    @Column(name = "activity")
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Basic
    @Column(name = "next_activity")
    public Date getNextActivity() {
        return nextActivity;
    }

    public void setNextActivity(Date nextActivity) {
        this.nextActivity = nextActivity;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "website")
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JoinColumn(name = "ranking_id")
    @OneToOne
    public ContactRank getRank() {
        return rank;
    }

    public void setRank(ContactRank rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

}
