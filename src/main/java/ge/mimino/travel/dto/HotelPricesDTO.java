package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateSerializeSupport;
import ge.mimino.travel.model.HotelPrices;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HotelPricesDTO {
    private Integer id;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date date;
    private HotelDTO hotel;
    private Integer hotelId;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Timestamp createDate;
    private Date from;
    private Date to;
    private Double singleFit;
    private Double singleGroup;
    private Double doubleFit;
    private Double doubleGroup;
    private Double tripleFit;
    private Double tripleGroup;
    private Double singleSupplementFit;
    private Double singleSupplementGroup;
    private HotelPriceDateRangesDTO dateRange;


    public static HotelPricesDTO parse(HotelPrices record) {
        HotelPricesDTO dto = new HotelPricesDTO();
        dto.setId(record.getId());
        dto.setDate(record.getDate());
        dto.setHotel(HotelDTO.parse(record.getHotel()));
        dto.setSingleFit(record.getSingleFit());
        dto.setSingleGroup(record.getSingleGroup());
        dto.setDoubleFit(record.getDoubleFit());
        dto.setDoubleGroup(record.getDoubleGroup());
        dto.setTripleFit(record.getTripleFit());
        dto.setTripleGroup(record.getTripleGroup());
        dto.setCreateDate(record.getCreateDate());
        dto.setSingleSupplementFit(record.getSingleSupplementFit());
        dto.setSingleSupplementGroup(record.getSingleSupplementGroup());
        dto.setDateRange(HotelPriceDateRangesDTO.parse(record.getDateRange()));
        return dto;
    }


    public static List<HotelPricesDTO> parseToList(List<HotelPrices> records) {
        ArrayList<HotelPricesDTO> list = new ArrayList<HotelPricesDTO>();
        for (HotelPrices record : records) {
            list.add(HotelPricesDTO.parse(record));
        }
        return list;
    }

    public HotelPriceDateRangesDTO getDateRange() {
        return dateRange;
    }

    public void setDateRange(HotelPriceDateRangesDTO dateRange) {
        this.dateRange = dateRange;
    }

    public Double getSingleSupplementFit() {
        return singleSupplementFit;
    }

    public void setSingleSupplementFit(Double singleSupplementFit) {
        this.singleSupplementFit = singleSupplementFit;
    }

    public Double getSingleSupplementGroup() {
        return singleSupplementGroup;
    }

    public void setSingleSupplementGroup(Double singleSupplementGroup) {
        this.singleSupplementGroup = singleSupplementGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public HotelDTO getHotel() {
        return hotel;
    }

    public void setHotel(HotelDTO hotel) {
        this.hotel = hotel;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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

    public Double getSingleFit() {
        return singleFit;
    }

    public void setSingleFit(Double singleFit) {
        this.singleFit = singleFit;
    }

    public Double getSingleGroup() {
        return singleGroup;
    }

    public void setSingleGroup(Double singleGroup) {
        this.singleGroup = singleGroup;
    }

    public Double getDoubleFit() {
        return doubleFit;
    }

    public void setDoubleFit(Double doubleFit) {
        this.doubleFit = doubleFit;
    }

    public Double getDoubleGroup() {
        return doubleGroup;
    }

    public void setDoubleGroup(Double doubleGroup) {
        this.doubleGroup = doubleGroup;
    }

    public Double getTripleFit() {
        return tripleFit;
    }

    public void setTripleFit(Double tripleFit) {
        this.tripleFit = tripleFit;
    }

    public Double getTripleGroup() {
        return tripleGroup;
    }

    public void setTripleGroup(Double tripleGroup) {
        this.tripleGroup = tripleGroup;
    }
}
