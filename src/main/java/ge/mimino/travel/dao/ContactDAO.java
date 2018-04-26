package ge.mimino.travel.dao;


import ge.mimino.travel.model.Contact;
import ge.mimino.travel.model.ContactCategories;
import ge.mimino.travel.model.ContactTypes;
import ge.mimino.travel.request.AddContactRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public List<Contact> getContacts(int start, int limit, AddContactRequest srchRequest) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Contact.class.getSimpleName()).append(" e Where 1=1 ");

        if (srchRequest.getId() != null && srchRequest.getId() > 0) {
            q.append(" and e.id ='").append(srchRequest.getId()).append("'");
        }
        if (srchRequest.getName() != null) {
            q.append(" and e.name like '%").append(srchRequest.getName()).append("%'");
        }
        if (srchRequest.getContactPerson() != null) {
            q.append(" and e.contactPerson like '%").append(srchRequest.getContactPerson()).append("%'");
        }
        if (srchRequest.getPhone() != null) {
            q.append(" and e.phone like '%").append(srchRequest.getPhone()).append("%'");
        }
        if (srchRequest.getEmail() != null) {
            q.append(" and e.email like '%").append(srchRequest.getEmail()).append("%'");
        }
        if (srchRequest.getCountryId() != null) {
            q.append(" and e.country.id ='").append(srchRequest.getCountryId()).append("'");
        }
        if (srchRequest.getRankId() != null) {
            q.append(" and e.rank.id ='").append(srchRequest.getRankId()).append("'");
        }
        if (srchRequest.getUserId() != null) {
            q.append(" and e.user.id ='").append(srchRequest.getUserId()).append("'");
        }
        if (srchRequest.getNextActivity() != null && srchRequest.getNextActivityTo() != null) {
            q.append(" and e.nextActivity between '").append(srchRequest.getNextActivity()).append("' and '").append(srchRequest.getNextActivityTo()).append("'");
        }

        TypedQuery<Contact> query = entityManager.createQuery(q.toString(), Contact.class);
        return query.setFirstResult(start).setMaxResults(limit).getResultList();
    }
}
