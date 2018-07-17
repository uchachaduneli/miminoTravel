package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "geo_object_images", schema = "mimino")
public class GeoObjectImages {
    private Integer id;
    private String name;
    private Integer geoObjectId;

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

    public GeoObjectImages() {
    }

    public GeoObjectImages(String name, Integer geoObjectId) {
        this.name = name;
        this.geoObjectId = geoObjectId;
    }

    @Basic
    @Column(name = "geo_object_id")
    public Integer getGeoObjectId() {
        return geoObjectId;
    }

    public void setGeoObjectId(Integer geoObjectId) {
        this.geoObjectId = geoObjectId;
    }
}
