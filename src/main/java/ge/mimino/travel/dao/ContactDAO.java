package ge.mimino.travel.dao;


import ge.mimino.travel.model.ContactCategories;
import ge.mimino.travel.model.ContactTypes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ME.
 */

@Repository
public class ContactDAO extends AbstractDAO {

    @PersistenceContext(unitName = "mimino")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public int removeContactCategories(Integer contactId) {
        return entityManager.createQuery("delete from " + ContactCategories.class.getSimpleName() + " c where c.contactId=" + contactId).executeUpdate();
    }

    public int removeContactTypes(Integer contactId) {
        return entityManager.createQuery("delete from " + ContactTypes.class.getSimpleName() + " c where c.contactId=" + contactId).executeUpdate();
    }
}
