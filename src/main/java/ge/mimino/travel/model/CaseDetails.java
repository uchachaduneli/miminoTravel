package ge.mimino.travel.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "case_details", schema = "university")
public class CaseDetails {
    private Integer id;
    private Integer caseId;
    private String detail;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "case_id")
    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
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
        CaseDetails that = (CaseDetails) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(caseId, that.caseId) &&
                Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, caseId, detail);
    }
}
