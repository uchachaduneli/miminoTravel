package ge.mimino.travel.dto;

import ge.mimino.travel.model.RestaurantMealCategories;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMealCatsDTO {

    private Integer id;
    private Integer restaurantId;
    private String categoryName;
    private Double price;
    private String group;


    public static RestaurantMealCatsDTO parse(RestaurantMealCategories record) {
        RestaurantMealCatsDTO dto = new RestaurantMealCatsDTO();
        dto.setId(record.getId());
        dto.setRestaurantId(record.getRestaurantId());
        dto.setPrice(record.getPrice());
        dto.setCategoryName(record.getCategoryName());
        dto.setGroup(record.getGroup());
        return dto;
    }


    public static List<RestaurantMealCatsDTO> parseToList(List<RestaurantMealCategories> records) {
        ArrayList<RestaurantMealCatsDTO> list = new ArrayList<RestaurantMealCatsDTO>();
        for (RestaurantMealCategories record : records) {
            list.add(RestaurantMealCatsDTO.parse(record));
        }
        return list;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
