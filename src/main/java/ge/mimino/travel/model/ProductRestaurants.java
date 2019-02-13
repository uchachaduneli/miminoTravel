package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_restaurants", schema = "mimino")
public class ProductRestaurants {
    private Integer id;
    private Restaurant restaurant;
    private Integer requestId;
    private Integer day;
    private String mealCategories;
    private String packages;
    private Double price;

    public ProductRestaurants() {
    }

    public ProductRestaurants(Restaurant restaurant, Integer requestId, Integer day, String mealCategories, String packages, Double price) {
        this.restaurant = restaurant;
        this.requestId = requestId;
        this.day = day;
        this.mealCategories = mealCategories;
        this.packages = packages;
        this.price = price;
    }

    public ProductRestaurants(Restaurant restaurant, Integer requestId, Integer day, String mealCategories, String packages) {
        this.restaurant = restaurant;
        this.requestId = requestId;
        this.day = day;
        this.mealCategories = mealCategories;
        this.packages = packages;
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

    @JoinColumn(name = "restaurant_id")
    @OneToOne
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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

    @Basic
    @Column(name = "packages", nullable = true, length = -1)
    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
