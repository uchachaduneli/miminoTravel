package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_meal_categories", schema = "mimino")
public class RestaurantMealCategories {
    private Integer id;
    private Integer restaurantId;
    private String categoryName;
    private Double price;
    private String group;

    public RestaurantMealCategories(Integer restaurantId, String categoryName, Double price, String group) {
        this.restaurantId = restaurantId;
        this.categoryName = categoryName;
        this.price = price;
        this.group = group;
    }

    public RestaurantMealCategories() {
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
    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "`group`")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
