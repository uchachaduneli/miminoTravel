package ge.mimino.travel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "request_details", schema = "university")
public class RequestDetails {
    private Integer id;
    private Integer requestId;
    private String detail;

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
    @Column(name = "request_id")
    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @Basic
    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestDetails that = (RequestDetails) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, requestId, detail);
    }
}
