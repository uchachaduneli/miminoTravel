package ge.mimino.travel.dto;

import ge.mimino.travel.model.HotelImages;

import java.util.ArrayList;
import java.util.List;

public class HotelImagesDTO {

    private Integer id;
    private Integer hotelId;
    private String name;


    public static HotelImagesDTO parse(HotelImages record) {
        HotelImagesDTO dto = new HotelImagesDTO();
        dto.setId(record.getId());
        dto.setHotelId(record.getHotelId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<HotelImagesDTO> parseToList(List<HotelImages> records) {
        ArrayList<HotelImagesDTO> list = new ArrayList<HotelImagesDTO>();
        for (HotelImages record : records) {
            list.add(HotelImagesDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
