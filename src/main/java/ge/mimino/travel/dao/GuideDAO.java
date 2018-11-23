package ge.mimino.travel.dao;


import ge.mimino.travel.model.Guide;
import ge.mimino.travel.model.GuidePrices;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ME.
 */

@Repository
public class GuideDAO extends AbstractDAO {

  @PersistenceContext(unitName = "mimino")
  private EntityManager entityManager;

  @Override
  public EntityManager getEntityManager() {
    return entityManager;
  }


  public List<Guide> getGuides(int start, int limit, Guide srchRequest) {
    StringBuilder q = new StringBuilder();
    q.append("Select e From ").append(Guide.class.getSimpleName()).append(" e Where 1=1 ");

    if (srchRequest.getId() != null && srchRequest.getId() > 0) {
      q.append(" and e.id ='").append(srchRequest.getId()).append("'");
    }
    if (srchRequest.getType() != null && srchRequest.getType() > 0) {
      q.append(" and e.type ='").append(srchRequest.getType()).append("'");
    }
    if (srchRequest.getName() != null) {
      q.append(" and e.name like '%").append(srchRequest.getName()).append("%'");
    }

    TypedQuery<Guide> query = entityManager.createQuery(q.toString(), Guide.class);
    return query.setFirstResult(start).setMaxResults(limit).getResultList();
  }

  public int removeGuidePrices(Integer guideId) {
    return entityManager.createQuery("delete from " + GuidePrices.class.getSimpleName() + " c where c.guide.id=" + guideId).executeUpdate();
  }
}
