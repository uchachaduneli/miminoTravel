package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateSerializeSupport;
import ge.mimino.travel.model.HotelPriceDateRanges;
import ge.mimino.travel.model.HotelPrices;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HotelPriceDateRangesDTO {
    private Integer id;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date from;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date to;

    public static HotelPriceDateRangesDTO parse(HotelPriceDateRanges record) {
        HotelPriceDateRangesDTO dto = new HotelPriceDateRangesDTO();
        dto.setId(record.getId());
        dto.setFrom(record.getFrom());
        dto.setTo(record.getTo());
        return dto;
    }


    public static List<HotelPriceDateRangesDTO> parseToList(List<HotelPriceDateRanges> records) {
        ArrayList<HotelPriceDateRangesDTO> list = new ArrayList<HotelPriceDateRangesDTO>();
        for (HotelPriceDateRanges record : records) {
            list.add(HotelPriceDateRangesDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
