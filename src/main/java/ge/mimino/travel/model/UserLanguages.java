package ge.mimino.travel.model;

import javax.persistence.*;

@Entity
@Table(name = "user_languages", schema = "university")
public class UserLanguages {
    private Integer id;
    private Integer userId;
    private Language language;

    public UserLanguages(Integer userId, Language language) {
        this.userId = userId;
        this.language = language;
    }

    public UserLanguages() {
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
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JoinColumn(name = "language_id")
    @OneToOne
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
