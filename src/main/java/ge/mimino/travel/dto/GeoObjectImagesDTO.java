package ge.mimino.travel.dto;

import ge.mimino.travel.model.GeoObjectImages;

import java.util.ArrayList;
import java.util.List;

public class GeoObjectImagesDTO {

    private Integer id;
    private GeoObjectDTO geoObject;
    private Integer geoObjectId;
    private String name;


    public static GeoObjectImagesDTO parse(GeoObjectImages record) {
        GeoObjectImagesDTO dto = new GeoObjectImagesDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setGeoObject(GeoObjectDTO.parse(record.getGeoObject()));
        dto.setGeoObjectId(record.getGeoObject().getId());
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

    public GeoObjectDTO getGeoObject() {
        return geoObject;
    }

    public void setGeoObject(GeoObjectDTO geoObject) {
        this.geoObject = geoObject;
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
