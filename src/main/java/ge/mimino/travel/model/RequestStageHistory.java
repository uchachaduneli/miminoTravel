package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "request_stage_history", schema = "mimino")
public class RequestStageHistory {
    private Integer id;
    private Stage stage;
    private Integer requestId;
    private Timestamp createDate;
    private Users user;

    public RequestStageHistory() {
    }

    public RequestStageHistory(Stage stage, Integer requestId, Users user) {
        this.stage = stage;
        this.requestId = requestId;
        this.user = user;
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

    @JoinColumn(name = "stage_id")
    @OneToOne
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
    @Column(name = "create_date", updatable = false, insertable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @JoinColumn(name = "user_id")
    @OneToOne
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
