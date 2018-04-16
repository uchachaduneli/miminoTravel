package ge.mimino.travel.dto;

import ge.mimino.travel.model.ContactType;

import java.util.ArrayList;
import java.util.List;

public class ContactTypeDTO {

    private Integer id;
    private String name;


    public static ContactTypeDTO parse(ContactType record) {
        ContactTypeDTO dto = new ContactTypeDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<ContactTypeDTO> parseToList(List<ContactType> records) {
        ArrayList<ContactTypeDTO> list = new ArrayList<ContactTypeDTO>();
        for (ContactType record : records) {
            list.add(ContactTypeDTO.parse(record));
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
