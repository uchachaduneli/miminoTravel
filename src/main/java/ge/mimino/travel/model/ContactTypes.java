package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "contact_types")
public class ContactTypes {
    private Integer id;
    private Integer contactId;
    private ContactType type;

    public ContactTypes(Integer contactId, ContactType type) {
        this.contactId = contactId;
        this.type = type;
    }

    public ContactTypes() {
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
    @Column(name = "contact_id")
    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    @JoinColumn(name = "type_id")
    @OneToOne
    public ContactType getContactType() {
        return type;
    }

    public void setContactType(ContactType type) {
        this.type = type;
    }

}
