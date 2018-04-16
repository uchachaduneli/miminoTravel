package ge.mimino.travel.request;

import ge.mimino.travel.model.ContactRank;
import ge.mimino.travel.model.Country;

import java.sql.Date;
import java.sql.Timestamp;

public class AddContactRequest {
    private Integer id;
    private String name;
    private String contactPerson;
    private String info;
    private Date lastActivity;
    private String activity;
    private Date nextActivity;
    private String phone;
    private String website;
    private Integer countryId;
    private Country country;
    private String city;
    private Integer rankId;
    private ContactRank rank;
    private String source;
    private Timestamp createDate;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public ContactRank getRank() {
        return rank;
    }

    public void setRank(ContactRank rank) {
        this.rank = rank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getNextActivity() {
        return nextActivity;
    }

    public void setNextActivity(Date nextActivity) {
        this.nextActivity = nextActivity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
