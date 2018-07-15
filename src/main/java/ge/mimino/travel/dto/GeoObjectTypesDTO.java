package ge.mimino.travel.dto;

import ge.mimino.travel.model.GeoObjectTypes;

import java.util.ArrayList;
import java.util.List;

public class GeoObjectTypesDTO {

    private Integer id;
    private String name;


    public static GeoObjectTypesDTO parse(GeoObjectTypes record) {
        GeoObjectTypesDTO dto = new GeoObjectTypesDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<GeoObjectTypesDTO> parseToList(List<GeoObjectTypes> records) {
        ArrayList<GeoObjectTypesDTO> list = new ArrayList<GeoObjectTypesDTO>();
        for (GeoObjectTypes record : records) {
            list.add(GeoObjectTypesDTO.parse(record));
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
