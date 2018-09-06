package ge.mimino.travel.dto;

import ge.mimino.travel.model.ProductRestaurants;

import java.util.ArrayList;
import java.util.List;

public class ProductRestaurantsDTO {

    private Integer id;
    private Integer restaurantId;
    private Integer requestId;
    private Integer day;
    private String mealCategories;


    public static ProductRestaurantsDTO parse(ProductRestaurants record) {
        ProductRestaurantsDTO dto = new ProductRestaurantsDTO();
        dto.setId(record.getId());
        dto.setRestaurantId(record.getRestaurantId());
        dto.setRequestId(record.getRequestId());
        dto.setDay(record.getDay());
        dto.setMealCategories(record.getMealCategories());
        return dto;
    }


    public static List<ProductRestaurantsDTO> parseToList(List<ProductRestaurants> records) {
        ArrayList<ProductRestaurantsDTO> list = new ArrayList<ProductRestaurantsDTO>();
        for (ProductRestaurants record : records) {
            list.add(ProductRestaurantsDTO.parse(record));
        }
        return list;
    }

    public String getMealCategories() {
        return mealCategories;
    }

    public void setMealCategories(String mealCategories) {
        this.mealCategories = mealCategories;
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

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
