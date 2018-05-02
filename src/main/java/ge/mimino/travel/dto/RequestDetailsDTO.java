package ge.mimino.travel.dto;

import ge.mimino.travel.model.RequestDetails;

import java.util.ArrayList;
import java.util.List;

public class RequestDetailsDTO {

    private Integer id;
    private Integer caseId;
    private String detail;


    public static RequestDetailsDTO parse(RequestDetails record) {
        RequestDetailsDTO dto = new RequestDetailsDTO();
        dto.setId(record.getId());
        dto.setCaseId(record.getRequestId());
        dto.setDetail(record.getDetail());
        return dto;
    }


    public static List<RequestDetailsDTO> parseToList(List<RequestDetails> records) {
        ArrayList<RequestDetailsDTO> list = new ArrayList<RequestDetailsDTO>();
        for (RequestDetails record : records) {
            list.add(RequestDetailsDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
