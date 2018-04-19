package ge.mimino.travel.dto;

import ge.mimino.travel.model.ContactCategory;

import java.util.ArrayList;
import java.util.List;

public class ContactCategoryDTO {

    private Integer id;
    private String name;


    public static ContactCategoryDTO parse(ContactCategory record) {
        ContactCategoryDTO dto = new ContactCategoryDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<ContactCategoryDTO> parseToList(List<ContactCategory> records) {
        ArrayList<ContactCategoryDTO> list = new ArrayList<ContactCategoryDTO>();
        for (ContactCategory record : records) {
            list.add(ContactCategoryDTO.parse(record));
        }
        return list;
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
}
