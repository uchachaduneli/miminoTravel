package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_package", schema = "mimino")
public class RestaurantPackage {
    private Integer id;
    private Integer restaurantId;
    private String name;

    public RestaurantPackage() {
    }

    public RestaurantPackage(Integer restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "restaurant_id", nullable = false)
    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
