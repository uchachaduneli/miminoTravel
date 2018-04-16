package ge.mimino.travel.dto;

import ge.mimino.travel.model.ContactTypes;

import java.util.ArrayList;
import java.util.List;

public class ContactTypesDTO {

    private Integer id;
    private Integer contactId;
    private ContactTypeDTO type;


    public static ContactTypesDTO parse(ContactTypes record) {
        ContactTypesDTO dto = new ContactTypesDTO();
        dto.setId(record.getId());
        dto.setContactId(record.getContactId());
        dto.setType(ContactTypeDTO.parse(record.getContactType()));
        return dto;
    }


    public static List<ContactTypesDTO> parseToList(List<ContactTypes> records) {
        ArrayList<ContactTypesDTO> list = new ArrayList<ContactTypesDTO>();
        for (ContactTypes record : records) {
            list.add(ContactTypesDTO.parse(record));
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

    public ContactTypeDTO getType() {
        return type;
    }

    public void setType(ContactTypeDTO type) {
        this.type = type;
    }
}
