package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateSerializeSupport;
import ge.mimino.travel.model.HotelPrices;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HotelPricesDTO {
    private Integer id;
    private String date;
    private HotelDTO hotel;
    private Double usd;
    private Double eur;
    private Double gel;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Timestamp createDate;


    public static HotelPricesDTO parse(HotelPrices record) {
        HotelPricesDTO dto = new HotelPricesDTO();
        dto.setId(record.getId());
        dto.setDate(record.getDate());
        dto.setHotel(HotelDTO.parse(record.getHotel()));
        dto.setUsd(record.getUsd());
        dto.setEur(record.getEur());
        dto.setGel(record.getGel());
        dto.setCreateDate(record.getCreateDate());
        return dto;
    }


    public static List<HotelPricesDTO> parseToList(List<HotelPrices> records) {
        ArrayList<HotelPricesDTO> list = new ArrayList<HotelPricesDTO>();
        for (HotelPrices record : records) {
            list.add(HotelPricesDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HotelDTO getHotel() {
        return hotel;
    }

    public void setHotel(HotelDTO hotel) {
        this.hotel = hotel;
    }

    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    public Double getEur() {
        return eur;
    }

    public void setEur(Double eur) {
        this.eur = eur;
    }

    public Double getGel() {
        return gel;
    }

    public void setGel(Double gel) {
        this.gel = gel;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
