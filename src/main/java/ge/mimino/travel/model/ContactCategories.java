package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "contact_categories", schema = "university")
public class ContactCategories {
    private Integer id;
    private Integer contactId;
    private ContactCategory category;

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

    @JoinColumn(name = "category_id")
    @OneToOne
    public ContactCategory getCategory() {
        return category;
    }

    public void setCategory(ContactCategory category) {
        this.category = category;
    }
}
