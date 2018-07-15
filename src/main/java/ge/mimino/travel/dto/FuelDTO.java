package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateTimeSerializeSupport;
import ge.mimino.travel.model.Fuel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FuelDTO {

    private Integer id;
    private String name;
    private Double price;
    @JsonSerialize(using = JsonDateTimeSerializeSupport.class)
    private Timestamp updatedAt;


    public static FuelDTO parse(Fuel record) {
        FuelDTO dto = new FuelDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setPrice(record.getPrice());
        dto.setUpdatedAt(record.getUpdatedAt());
        return dto;
    }


    public static List<FuelDTO> parseToList(List<Fuel> records) {
        ArrayList<FuelDTO> list = new ArrayList<FuelDTO>();
        for (Fuel record : records) {
            list.add(FuelDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
