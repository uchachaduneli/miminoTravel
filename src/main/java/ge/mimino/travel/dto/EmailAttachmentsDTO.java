package ge.mimino.travel.dto;

import ge.mimino.travel.model.EmailAttachments;

import java.util.ArrayList;
import java.util.List;

public class EmailAttachmentsDTO {

    private Integer id;
    private Integer emailId;
    private String name;


    public static EmailAttachmentsDTO parse(EmailAttachments record) {
        EmailAttachmentsDTO dto = new EmailAttachmentsDTO();
        dto.setId(record.getId());
        dto.setEmailId(record.getEmailId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<EmailAttachmentsDTO> parseToList(List<EmailAttachments> records) {
        ArrayList<EmailAttachmentsDTO> list = new ArrayList<EmailAttachmentsDTO>();
        for (EmailAttachments record : records) {
            list.add(EmailAttachmentsDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmailId() {
        return emailId;
    }

    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
