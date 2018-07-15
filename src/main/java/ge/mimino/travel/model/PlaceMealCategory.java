package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "place_meal_category")
public class PlaceMealCategory {
    private Integer id;
    private MealCategory mealCategory;
    private Place place;
    private Request request;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JoinColumn(name = "meal_category_id")
    @OneToOne
    public MealCategory getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(MealCategory mealCategory) {
        this.mealCategory = mealCategory;
    }

    @JoinColumn(name = "place_id")
    @OneToOne
    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @JoinColumn(name = "request_id")
    @OneToOne
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

}
