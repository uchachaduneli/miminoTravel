package ge.mimino.travel.dto;

import ge.mimino.travel.model.CaseCountry;

import java.util.ArrayList;
import java.util.List;

public class CaseCountryDTO {

    private Integer id;
    private Integer caseId;
    private CountryDTO country;
    private Integer daysCount;
    private String note;


    public static CaseCountryDTO parse(CaseCountry record) {
        CaseCountryDTO dto = new CaseCountryDTO();
        dto.setId(record.getId());
        dto.setCaseId(record.getCaseId());
        dto.setCountry(CountryDTO.parse(record.getCountry()));
        dto.setDaysCount(record.getDaysCount());
        dto.setNote(record.getNote());
        return dto;
    }


    public static List<CaseCountryDTO> parseToList(List<CaseCountry> records) {
        ArrayList<CaseCountryDTO> list = new ArrayList<CaseCountryDTO>();
        for (CaseCountry record : records) {
            list.add(CaseCountryDTO.parse(record));
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

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public Integer getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(Integer daysCount) {
        this.daysCount = daysCount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
