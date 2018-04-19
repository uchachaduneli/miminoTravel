package ge.mimino.travel.dto;

import ge.mimino.travel.model.ContactStatusHistory;

import java.util.ArrayList;
import java.util.List;

public class ContactStatusHistoryDTO {

    private Integer id;
    private Integer contactId;
    private ContactStatusDTO status;


    public static ContactStatusHistoryDTO parse(ContactStatusHistory record) {
        ContactStatusHistoryDTO dto = new ContactStatusHistoryDTO();
        dto.setId(record.getId());
        dto.setContactId(record.getContactId());
        dto.setStatus(ContactStatusDTO.parse(record.getStatus()));
        return dto;
    }


    public static List<ContactStatusHistoryDTO> parseToList(List<ContactStatusHistory> records) {
        ArrayList<ContactStatusHistoryDTO> list = new ArrayList<ContactStatusHistoryDTO>();
        for (ContactStatusHistory record : records) {
            list.add(ContactStatusHistoryDTO.parse(record));
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

    public ContactStatusDTO getStatus() {
        return status;
    }

    public void setStatus(ContactStatusDTO status) {
        this.status = status;
    }
}
