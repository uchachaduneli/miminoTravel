package ge.mimino.travel.dto;

import ge.mimino.travel.model.Region;

import java.util.ArrayList;
import java.util.List;

public class RegionDTO {

    private Integer id;
    private String name;


    public static RegionDTO parse(Region record) {
        RegionDTO dto = new RegionDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<RegionDTO> parseToList(List<Region> records) {
        ArrayList<RegionDTO> list = new ArrayList<RegionDTO>();
        for (Region record : records) {
            list.add(RegionDTO.parse(record));
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
