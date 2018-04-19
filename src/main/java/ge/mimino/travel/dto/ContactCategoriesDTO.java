package ge.mimino.travel.dto;

import ge.mimino.travel.model.ContactCategories;

import java.util.ArrayList;
import java.util.List;

public class ContactCategoriesDTO {

    private Integer id;
    private Integer contactId;
    private ContactCategoryDTO category;


    public static ContactCategoriesDTO parse(ContactCategories record) {
        ContactCategoriesDTO dto = new ContactCategoriesDTO();
        dto.setId(record.getId());
        dto.setContactId(record.getContactId());
        dto.setCategory(ContactCategoryDTO.parse(record.getCategory()));
        return dto;
    }


    public static List<ContactCategoriesDTO> parseToList(List<ContactCategories> records) {
        ArrayList<ContactCategoriesDTO> list = new ArrayList<ContactCategoriesDTO>();
        for (ContactCategories record : records) {
            list.add(ContactCategoriesDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public ContactCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(ContactCategoryDTO category) {
        this.category = category;
    }
}
