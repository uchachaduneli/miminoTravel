package ge.mimino.travel.dao;


import ge.mimino.travel.dto.DistancesDTO;
import ge.mimino.travel.model.Distances;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ME.
 */

@Repository
public class DistancesDAO extends AbstractDAO {

  @PersistenceContext(unitName = "mimino")
  private EntityManager entityManager;

  @Override
  public EntityManager getEntityManager() {
    return entityManager;
  }


  public List<Distances> getDistances(int start, int limit, DistancesDTO srchRequest) {
    StringBuilder q = new StringBuilder();
    q.append("Select e From ").append(Distances.class.getSimpleName()).append(" e Where 1=1 ");

    if (srchRequest.getId() != null && srchRequest.getId() > 0) {
      q.append(" and e.id ='").append(srchRequest.getId()).append("'");
    }

    if (srchRequest.getFromPlaceId() != null) {
      q.append(" and e.fromPlace.id like '%").append(srchRequest.getFromPlaceId()).append("%'");
    }

    if (srchRequest.getToPlaceId() != null) {
      q.append(" and e.toPlace.id like '%").append(srchRequest.getToPlaceId()).append("%'");
    }

    TypedQuery<Distances> query = entityManager.createQuery(q.toString(), Distances.class);
    return query.setFirstResult(start).setMaxResults(limit).getResultList();
  }
}
