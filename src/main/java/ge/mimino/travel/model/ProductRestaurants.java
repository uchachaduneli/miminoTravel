package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_restaurants", schema = "mimino")
public class ProductRestaurants {
    private Integer id;
    private Integer restaurantId;
    private Integer requestId;
    private Integer day;
    private String mealCategories;

    public ProductRestaurants() {
    }

    public ProductRestaurants(Integer restaurantId, Integer requestId, Integer day, String mealCategories) {
        this.restaurantId = restaurantId;
        this.requestId = requestId;
        this.day = day;
        this.mealCategories = mealCategories;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "restaurant_id")
    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Basic
    @Column(name = "request_id")
    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @Basic
    @Column(name = "day")
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Basic
    @Column(name = "meal_categories", length = 100)
    public String getMealCategories() {
        return mealCategories;
    }

    public void setMealCategories(String mealCategories) {
        this.mealCategories = mealCategories;
    }
}
