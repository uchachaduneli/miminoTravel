package ge.mimino.travel.dao;


import ge.mimino.travel.dto.NonstandartServiceDTO;
import ge.mimino.travel.model.NonstandartService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ME.
 */

@Repository
public class NonstandartDAO extends AbstractDAO {

    @PersistenceContext(unitName = "mimino")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


    public List<NonstandartService> getNonStandartServices(int start, int limit, NonstandartServiceDTO srchRequest) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(NonstandartService.class.getSimpleName()).append(" e Where 1=1 ");
//
//        if (srchRequest.getId() != null && srchRequest.getId() > 0) {
//            q.append(" and e.id ='").append(srchRequest.getId()).append("'");
//        }
//        if (srchRequest.getName() != null) {
//            q.append(" and e.name like '%").append(srchRequest.getName()).append("%'");
//        }
//        if (srchRequest.getContactPerson() != null) {
//            q.append(" and e.contactPerson like '%").append(srchRequest.getContactPerson()).append("%'");
//        }
//        if (srchRequest.getPhone() != null) {
//            q.append(" and e.phone like '%").append(srchRequest.getPhone()).append("%'");
//        }
//        if (srchRequest.getEmail() != null) {
//            q.append(" and e.email like '%").append(srchRequest.getEmail()).append("%'");
//        }
//        if (srchRequest.getCountryId() != null) {
//            q.append(" and e.country.id ='").append(srchRequest.getCountryId()).append("'");
//        }
//        if (srchRequest.getRankId() != null) {
//            q.append(" and e.rank.id ='").append(srchRequest.getRankId()).append("'");
//        }
//        if (srchRequest.getUserId() != null) {
//            q.append(" and e.user.id ='").append(srchRequest.getUserId()).append("'");
//        }
//        if (srchRequest.getNextActivity() != null && srchRequest.getNextActivityTo() != null) {
//            q.append(" and e.nextActivity between '").append(srchRequest.getNextActivity()).append("' and '").append(srchRequest.getNextActivityTo()).append("'");
//        }

        TypedQuery<NonstandartService> query = entityManager.createQuery(q.toString(), NonstandartService.class);
        return query.setFirstResult(start).setMaxResults(limit).getResultList();
    }
}
