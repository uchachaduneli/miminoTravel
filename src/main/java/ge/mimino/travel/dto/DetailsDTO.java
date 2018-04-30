package ge.mimino.travel.dto;

import ge.mimino.travel.model.Details;

import java.util.ArrayList;
import java.util.List;

public class DetailsDTO {

    private Integer id;
    private String name;


    public static DetailsDTO parse(Details record) {
        DetailsDTO dto = new DetailsDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<DetailsDTO> parseToList(List<Details> records) {
        ArrayList<DetailsDTO> list = new ArrayList<DetailsDTO>();
        for (Details record : records) {
            list.add(DetailsDTO.parse(record));
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
