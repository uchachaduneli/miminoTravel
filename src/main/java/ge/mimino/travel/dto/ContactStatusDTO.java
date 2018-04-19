package ge.mimino.travel.dto;

import ge.mimino.travel.model.ContactStatus;

import java.util.ArrayList;
import java.util.List;

public class ContactStatusDTO {

    private Integer id;
    private String name;


    public static ContactStatusDTO parse(ContactStatus record) {
        ContactStatusDTO dto = new ContactStatusDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<ContactStatusDTO> parseToList(List<ContactStatus> records) {
        ArrayList<ContactStatusDTO> list = new ArrayList<ContactStatusDTO>();
        for (ContactStatus record : records) {
            list.add(ContactStatusDTO.parse(record));
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
