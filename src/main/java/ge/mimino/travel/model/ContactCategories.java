package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "contact_categories")
public class ContactCategories {
    private Integer id;
    private Integer contactId;
    private ContactCategory category;

    public ContactCategories(Integer contactId, ContactCategory category) {
        this.contactId = contactId;
        this.category = category;
    }

    public ContactCategories() {
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

    @JoinColumn(name = "category_id")
    @OneToOne
    public ContactCategory getCategory() {
        return category;
    }

    public void setCategory(ContactCategory category) {
        this.category = category;
    }
}
