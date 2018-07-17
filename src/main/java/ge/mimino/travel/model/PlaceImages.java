package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "place_images", schema = "mimino")
public class PlaceImages {
    private Integer id;
    private String name;
    private Integer placeId;

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
    @Column(name = "name", nullable = false, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlaceImages() {
    }

    public PlaceImages(String name, Integer placeId) {
        this.name = name;
        this.placeId = placeId;
    }

    @Basic
    @Column(name = "place_id")
    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }
}
