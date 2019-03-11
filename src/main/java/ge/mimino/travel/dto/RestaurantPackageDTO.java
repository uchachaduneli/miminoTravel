package ge.mimino.travel.dto;

import ge.mimino.travel.model.RestaurantPackage;

import java.util.ArrayList;
import java.util.List;

public class RestaurantPackageDTO {

    private Integer id;
    private Integer restaurantId;
    private String name;
    private Double price;


    public static RestaurantPackageDTO parse(RestaurantPackage record) {
        RestaurantPackageDTO dto = new RestaurantPackageDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setRestaurantId(record.getRestaurantId());
        dto.setPrice(record.getPrice());
        return dto;
    }


    public static List<RestaurantPackageDTO> parseToList(List<RestaurantPackage> records) {
        ArrayList<RestaurantPackageDTO> list = new ArrayList<RestaurantPackageDTO>();
        for (RestaurantPackage record : records) {
            list.add(RestaurantPackageDTO.parse(record));
        }
        return list;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
