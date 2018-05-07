package ge.mimino.travel.dao;

import ge.mimino.travel.model.RequestCountry;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ME.
 */

@Repository
public class RequestDAO extends AbstractDAO {

    @PersistenceContext(unitName = "mimino")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public int removeRequestCountries(Integer requestId) {
        return entityManager.createQuery("delete from " + RequestCountry.class.getSimpleName() + " c where c.requestId=" + requestId).executeUpdate();
    }
}
