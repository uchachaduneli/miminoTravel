package ge.mimino.travel.dto;

import ge.mimino.travel.model.RequestCountry;

import java.util.ArrayList;
import java.util.List;

public class RequestCountryDTO {

    private Integer id;
    private Integer requestId;
    private CountryDTO country;
    private Integer daysCount;
    private String note;


    public static RequestCountryDTO parse(RequestCountry record) {
        RequestCountryDTO dto = new RequestCountryDTO();
        dto.setId(record.getId());
        dto.setRequestId(record.getRequestId());
        dto.setCountry(CountryDTO.parse(record.getCountry()));
        dto.setDaysCount(record.getDaysCount());
        dto.setNote(record.getNote());
        return dto;
    }


    public static List<RequestCountryDTO> parseToList(List<RequestCountry> records) {
        ArrayList<RequestCountryDTO> list = new ArrayList<RequestCountryDTO>();
        for (RequestCountry record : records) {
            list.add(RequestCountryDTO.parse(record));
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
