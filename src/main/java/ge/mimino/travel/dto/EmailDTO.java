package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateTimeSerializeSupport;
import ge.mimino.travel.model.Email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailDTO {

    private Integer id;
    private UsersDTO user;
    private String from;
    private String to;
    private String subject;
    @JsonSerialize(using = JsonDateTimeSerializeSupport.class)
    private Date sendDate;
    @JsonSerialize(using = JsonDateTimeSerializeSupport.class)
    private Date receiveDate;
    private String content;
    private String attachments;
    @JsonSerialize(using = JsonDateTimeSerializeSupport.class)
    private Date insertDate;


    public static EmailDTO parse(Email record) {
        EmailDTO dto = new EmailDTO();
        dto.setId(record.getId());
        dto.setUser(UsersDTO.parse(record.getUser()));
        dto.setFrom(record.getFrom());
        dto.setTo(record.getTo());
        dto.setSubject(record.getSubject());
        dto.setSendDate(record.getSendDate());
        dto.setReceiveDate(record.getReceiveDate());
        dto.setContent(record.getContent());
        dto.setAttachments(record.getAttachments());
        dto.setInsertDate(record.getInsertDate());
        return dto;
    }


    public static List<EmailDTO> parseToList(List<Email> records) {
        ArrayList<EmailDTO> list = new ArrayList<EmailDTO>();
        for (Email record : records) {
            list.add(EmailDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
}
