package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateTimeSerializeSupport;
import ge.mimino.travel.model.City;
import ge.mimino.travel.model.RequestStageHistory;
import ge.mimino.travel.model.Stage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RequestStageHistoryDTO {

    private Integer id;
    private StageDTO stage;
    private Integer requestId;
    @JsonSerialize(using = JsonDateTimeSerializeSupport.class)
    private Timestamp createDate;
    private UsersDTO user;


    public static RequestStageHistoryDTO parse(RequestStageHistory record) {
        RequestStageHistoryDTO dto = new RequestStageHistoryDTO();
        dto.setId(record.getId());
        dto.setStage(StageDTO.parse(record.getStage()));
        dto.setCreateDate(record.getCreateDate());
        dto.setUser(UsersDTO.parse(record.getUser()));
        return dto;
    }


    public static List<RequestStageHistoryDTO> parseToList(List<RequestStageHistory> records) {
        ArrayList<RequestStageHistoryDTO> list = new ArrayList<RequestStageHistoryDTO>();
        for (RequestStageHistory record : records) {
            list.add(RequestStageHistoryDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StageDTO getStage() {
        return stage;
    }

    public void setStage(StageDTO stage) {
        this.stage = stage;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }
}
