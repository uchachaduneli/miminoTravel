package ge.mimino.travel.dto;

import ge.mimino.travel.model.PlaceImages;

import java.util.ArrayList;
import java.util.List;

public class PlaceImagesDTO {

    private Integer id;
    private Integer placeId;
    private String name;


    public static PlaceImagesDTO parse(PlaceImages record) {
        PlaceImagesDTO dto = new PlaceImagesDTO();
        dto.setId(record.getId());
        dto.setPlaceId(record.getPlaceId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<PlaceImagesDTO> parseToList(List<PlaceImages> records) {
        ArrayList<PlaceImagesDTO> list = new ArrayList<PlaceImagesDTO>();
        for (PlaceImages record : records) {
            list.add(PlaceImagesDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
