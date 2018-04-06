package ge.mimino.travel.dto;

import ge.mimino.travel.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryDTO {

    private Integer id;
    private String name;


    public static CountryDTO parse(Country record) {
        CountryDTO dto = new CountryDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<CountryDTO> parseToList(List<Country> records) {
        ArrayList<CountryDTO> list = new ArrayList<CountryDTO>();
        for (Country record : records) {
            list.add(CountryDTO.parse(record));
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
