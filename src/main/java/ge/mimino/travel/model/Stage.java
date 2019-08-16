package ge.mimino.travel.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Stage {
    private Integer id;
    private String name;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        Stage stage = (Stage) o;
        return Objects.equals(id, stage.id) &&
                Objects.equals(name, stage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
