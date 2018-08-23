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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantPackage that = (RestaurantPackage) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (restaurantId != null ? !restaurantId.equals(that.restaurantId) : that.restaurantId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (restaurantId != null ? restaurantId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
