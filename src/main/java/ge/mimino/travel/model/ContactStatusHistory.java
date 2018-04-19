package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "contact_status_history", schema = "university")
public class ContactStatusHistory {
    private Integer id;
    private Integer contactId;
    private ContactStatus status;

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

    @JoinColumn(name = "status_id")
    @OneToOne
    public ContactStatus getStatus() {
        return status;
    }

    public void setStatus(ContactStatus status) {
        this.status = status;
    }

}
