package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateSerializeSupport;
import ge.mimino.travel.model.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDTO {

    private Integer userId;
    private String userDesc;
    private String userName;
    private String userPassword;
    private UsersTypeDTO type;
    private LanguageDTO language;
    private Integer deleted;
    private String email;
    private String emailPassword;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;

    public static int ADMINISTRATOR = 1;
    public static int COMUNICATION_MANAGER = 2;
    public static int PRODUCT_MANAGER = 3;
    public static int FINANCIAL_MANAGER = 4;
    public static int RESERVATION_MANAGER = 5;

    public static UsersDTO parse(Users record) {
        if (record != null) {
            UsersDTO dto = new UsersDTO();
            dto.setUserId(record.getUserId());
            dto.setUserDesc(record.getUserDesc());
            dto.setUserName(record.getUserName());
            dto.setUserPassword(record.getUserPassword());
            dto.setEmail(record.getEmail());
            dto.setEmailPassword(record.getEmailPassword());
            dto.setType(UsersTypeDTO.parse(record.getType()));
            dto.setLanguage(LanguageDTO.parse(record.getLanguage()));
            dto.setDeleted(record.getDeleted());
            dto.setCreateDate(record.getCreateDate());
            return dto;
        } else return null;
    }

    public static List<UsersDTO> parseToList(List<Users> records) {
        ArrayList<UsersDTO> list = new ArrayList<UsersDTO>();
        for (Users record : records) {
            list.add(UsersDTO.parse(record));
        }
        return list;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UsersTypeDTO getType() {
        return type;
    }

    public void setType(UsersTypeDTO type) {
        this.type = type;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }
}
