package ge.mimino.travel.dto;

import ge.mimino.travel.model.CaseDetails;

import java.util.ArrayList;
import java.util.List;

public class CaseDetailsDTO {

    private Integer id;
    private Integer caseId;
    private String detail;


    public static CaseDetailsDTO parse(CaseDetails record) {
        CaseDetailsDTO dto = new CaseDetailsDTO();
        dto.setId(record.getId());
        dto.setCaseId(record.getCaseId());
        dto.setDetail(record.getDetail());
        return dto;
    }


    public static List<CaseDetailsDTO> parseToList(List<CaseDetails> records) {
        ArrayList<CaseDetailsDTO> list = new ArrayList<CaseDetailsDTO>();
        for (CaseDetails record : records) {
            list.add(CaseDetailsDTO.parse(record));
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
