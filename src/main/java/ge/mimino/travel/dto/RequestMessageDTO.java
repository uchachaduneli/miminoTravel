package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonGeoDateTimeSerializeSupport;
import ge.mimino.travel.model.RequestMessage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RequestMessageDTO {

    private Integer id;
    private Integer requestId;
    private String message;
    private UsersDTO user;
    private Integer userId;
    @JsonSerialize(using = JsonGeoDateTimeSerializeSupport.class)
    private Timestamp createDate;


    public static RequestMessageDTO parse(RequestMessage record) {
        RequestMessageDTO dto = new RequestMessageDTO();
        dto.setId(record.getId());
        dto.setRequestId(record.getRequestId());
        dto.setMessage(record.getMessage());
        dto.setUser(UsersDTO.parse(record.getUser()));
        dto.setUserId(record.getUser().getUserId());
        dto.setCreateDate(record.getCreateDate());
        return dto;
    }


    public static List<RequestMessageDTO> parseToList(List<RequestMessage> records) {
        ArrayList<RequestMessageDTO> list = new ArrayList<RequestMessageDTO>();
        for (RequestMessage record : records) {
            list.add(RequestMessageDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
