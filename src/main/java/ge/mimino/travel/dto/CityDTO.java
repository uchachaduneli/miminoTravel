package ge.mimino.travel.dto;

import ge.mimino.travel.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityDTO {

    private Integer id;
    private String name;


    public static CityDTO parse(City record) {
        CityDTO dto = new CityDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<CityDTO> parseToList(List<City> records) {
        ArrayList<CityDTO> list = new ArrayList<CityDTO>();
        for (City record : records) {
            list.add(CityDTO.parse(record));
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
}
