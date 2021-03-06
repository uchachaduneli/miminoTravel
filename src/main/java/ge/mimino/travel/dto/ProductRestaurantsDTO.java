package ge.mimino.travel.dto;

import ge.mimino.travel.model.ProductRestaurants;

import java.util.ArrayList;
import java.util.List;

public class ProductRestaurantsDTO {

    private Integer id;
    private Integer restaurantId;
    private RestaurantDTO restaurant;
    private Integer requestId;
    private Integer day;
    private String mealCategories;
    private String packages;
    private Double price;


    public static ProductRestaurantsDTO parse(ProductRestaurants record) {
        ProductRestaurantsDTO dto = new ProductRestaurantsDTO();
        dto.setId(record.getId());
        dto.setRestaurantId(record.getRestaurant().getId());
        dto.setRestaurant(RestaurantDTO.parse(record.getRestaurant()));
        dto.setRequestId(record.getRequestId());
        dto.setDay(record.getDay());
        dto.setMealCategories(record.getMealCategories());
        dto.setPackages(record.getPackages());
        dto.setPrice(record.getPrice());
        return dto;
    }


    public static List<ProductRestaurantsDTO> parseToList(List<ProductRestaurants> records) {
        ArrayList<ProductRestaurantsDTO> list = new ArrayList<ProductRestaurantsDTO>();
        for (ProductRestaurants record : records) {
            list.add(ProductRestaurantsDTO.parse(record));
        }
        return list;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
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
