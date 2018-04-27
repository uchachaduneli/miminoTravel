package ge.mimino.travel.dao;


import ge.mimino.travel.model.UserLanguages;
import ge.mimino.travel.model.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ME.
 */

@Repository
public class UserDAO extends AbstractDAO {

    @PersistenceContext(unitName = "mimino")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Users login(String username, String password) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Users.class.getSimpleName())
                .append(" e Where e.userName ='").append(username).append("'")
                .append(" and e.userPassword ='").append(password).append("'");

        TypedQuery<Users> query = entityManager.createQuery(q.toString(), Users.class);
        List<Users> res = query.getResultList();
        return res.isEmpty() ? null : res.get(0);
    }

    public int removeUserLanguages(Integer id) {
        return entityManager.createQuery("delete from " + UserLanguages.class.getSimpleName() + " c where c.userId=" + id).executeUpdate();
    }
}
