package ge.mimino.travel.dao;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ME.
 */

@Repository
public class MiscDAO extends AbstractDAO {

    @PersistenceContext(unitName = "mimino")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
