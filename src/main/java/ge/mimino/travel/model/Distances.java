package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "distances")
public class Distances {
    private Integer id;
    private Place fromPlace;
    private Place toPlace;
    private Double distance;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JoinColumn(name = "from_place_id")
    @OneToOne
    public Place getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(Place fromPlace) {
        this.fromPlace = fromPlace;
    }

    @JoinColumn(name = "to_place_id")
    @OneToOne
    public Place getToPlace() {
        return toPlace;
    }

    public void setToPlace(Place toPlace) {
        this.toPlace = toPlace;
    }

    @Basic
    @Column(name = "distance", nullable = false, precision = 0)
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
