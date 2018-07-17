package ge.mimino.travel.dto;

import ge.mimino.travel.model.GeoObjectImages;

import java.util.ArrayList;
import java.util.List;

public class GeoObjectImagesDTO {

    private Integer id;
    private Integer geoObjectId;
    private String name;


    public static GeoObjectImagesDTO parse(GeoObjectImages record) {
        GeoObjectImagesDTO dto = new GeoObjectImagesDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setGeoObjectId(record.getGeoObjectId());
        return dto;
    }


    public static List<GeoObjectImagesDTO> parseToList(List<GeoObjectImages> records) {
        ArrayList<GeoObjectImagesDTO> list = new ArrayList<GeoObjectImagesDTO>();
        for (GeoObjectImages record : records) {
            list.add(GeoObjectImagesDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGeoObjectId() {
        return geoObjectId;
    }

    public void setGeoObjectId(Integer geoObjectId) {
        this.geoObjectId = geoObjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
