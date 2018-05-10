package ge.mimino.travel.dao;

import ge.mimino.travel.model.Request;
import ge.mimino.travel.model.RequestCountry;
import ge.mimino.travel.request.AddRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public List<Request> getRequests(int start, int limit, AddRequest srchRequest) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Request.class.getSimpleName()).append(" e Where 1=1 ");

        if (srchRequest.getId() != null && srchRequest.getId() > 0) {
            q.append(" and e.id ='").append(srchRequest.getId()).append("'");
        }
        if (srchRequest.getContactEmail() != null) {
            q.append(" and e.contactEmail like '%").append(srchRequest.getContactEmail()).append("%'");
        }
        if (srchRequest.getCombined() != null) {
            q.append(" and e.combined ='").append(srchRequest.getCombined()).append("'");
        }
        if (srchRequest.getPackageCategoryId() != null) {
            q.append(" and e.packageCategory.id ='").append(srchRequest.getPackageCategoryId()).append("'");
        }
        if (srchRequest.getCurrencyId() != null) {
            q.append(" and e.currency.id ='").append(srchRequest.getCurrencyId()).append("'");
        }
        if (srchRequest.getGuideLanguageId() != null) {
            q.append(" and e.guideLanguage.id ='").append(srchRequest.getGuideLanguageId()).append("'");
        }
        if (srchRequest.getUserId() != null && srchRequest.getUserId() > 0) {
            q.append(" and e.user.id ='").append(srchRequest.getUserId()).append("'");
        }
        if (srchRequest.getTourStart() != null && srchRequest.getTourStartTo() != null) {
            q.append(" and e.tourStart between '").append(srchRequest.getTourStart())
                    .append("' and '").append(srchRequest.getTourStartTo()).append("'");
        }
        if (srchRequest.getTourEnd() != null && srchRequest.getTourEndTo() != null) {
            q.append(" and e.tourEnd between '").append(srchRequest.getTourEnd())
                    .append("' and '").append(srchRequest.getTourEndTo()).append("'");
        }

        TypedQuery<Request> query = entityManager.createQuery(q.toString(), Request.class);
        return query.setFirstResult(start).setMaxResults(limit).getResultList();
    }
}
