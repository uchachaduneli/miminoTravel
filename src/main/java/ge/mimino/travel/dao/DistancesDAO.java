package ge.mimino.travel.dao;


import ge.mimino.travel.dto.DistancesDTO;
import ge.mimino.travel.model.Distances;
import ge.mimino.travel.model.Place;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

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

  public Map<String, Double> getDistancesByPlace(List<Integer> ids) {

//    Collections.sort(nodeList, new Comparator<DataNode>(){
//      public int compare(DataNode o1, DataNode o2){
//        if(o1.degree == o2.degree)
//          return 0;
//        return o1.degree < o2.degree ? -1 : 1;
//      }
//    });

    String q = "";
    List<Distances> distances = new ArrayList<>();
    Map<String, Double> res = new LinkedHashMap<>();
    for (int i = 0; i < ids.size(); i++) {
      distances.clear();
      Integer nextId;
      if (i + 1 < ids.size()) {
        nextId = ids.get(i + 1);
      } else {
        nextId = ids.get(i);
      }

      q = "Select e From " + Distances.class.getSimpleName()
              + " e Where  e.fromPlace.id= " + ids.get(i) +
              " and e.toPlace.id= " + nextId;

      Query query = entityManager.createQuery(q);
      distances = query.getResultList();
      if (!distances.isEmpty()) {
        res.put(distances.get(0).getFromPlace().getNameEn()+" "+ ids.get(i) + "  --> " + distances.get(0).getDistance()
                + " <-- " +" "+ nextId + distances.get(0).getToPlace().getNameEn(), distances.get(0).getDistance());
      } else {
        if (ids.get(i) != nextId) {
          query = entityManager.createQuery("Select e From " + Place.class.getSimpleName()
                  + " e Where  e.id= " + ids.get(i));
          Place fromPlace = (Place) query.getSingleResult();

          query = entityManager.createQuery("Select e From " + Place.class.getSimpleName()
                  + " e Where  e.id= " + nextId);
          Place toPlace = (Place) query.getSingleResult();

          res.put(fromPlace.getNameEn() + " --> (DISTANCE NOT FOUND) <-- " + toPlace.getNameEn(), 0.0);
        }
      }
    }

    return res;
  }
}
