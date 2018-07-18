package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "hotel_images", schema = "mimino")
public class HotelImages {
    private Integer id;
    private String name;
    private Integer hotelId;

    public HotelImages() {
    }

    public HotelImages(String name, Integer hotelId) {
        this.name = name;
        this.hotelId = hotelId;
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
    @Column(name = "name", nullable = false, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "hotel_id")
    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }
}
