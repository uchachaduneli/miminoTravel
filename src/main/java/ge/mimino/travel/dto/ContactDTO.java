package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateSerializeSupport;
import ge.mimino.travel.model.Contact;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactDTO {

    private Integer id;
    private String name;
    private String contactPerson;
    private String info;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date lastActivity;
    private String activity;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date nextActivity;
    private String phone;
    private String email;
    private String website;
    private CountryDTO country;
    private String city;
    private ContactRankDTO rank;
    private UsersDTO user;
    private String source;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Timestamp createDate;


    public static ContactDTO parse(Contact record) {
        ContactDTO dto = new ContactDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setContactPerson(record.getContactPerson());
        dto.setInfo(record.getInfo());
        dto.setLastActivity(record.getLastActivity());
        dto.setActivity(record.getActivity());
        dto.setNextActivity(record.getNextActivity());
        dto.setPhone(record.getPhone());
        dto.setEmail(record.getEmail());
        dto.setWebsite(record.getWebsite());
        dto.setCountry(CountryDTO.parse(record.getCountry()));
        dto.setCity(record.getCity());
        dto.setRank(ContactRankDTO.parse(record.getRank()));
        dto.setUser(UsersDTO.parse(record.getUser()));
        dto.setSource(record.getSource());
        dto.setCreateDate(record.getCreateDate());
        return dto;
    }


    public static List<ContactDTO> parseToList(List<Contact> records) {
        ArrayList<ContactDTO> list = new ArrayList<ContactDTO>();
        for (Contact record : records) {
            list.add(ContactDTO.parse(record));
        }
        return list;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
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

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ContactRankDTO getRank() {
        return rank;
    }

    public void setRank(ContactRankDTO rank) {
        this.rank = rank;
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
