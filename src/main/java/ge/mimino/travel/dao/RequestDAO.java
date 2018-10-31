package ge.mimino.travel.dao;

import ge.mimino.travel.dto.GeoObjectDTO;
import ge.mimino.travel.dto.ProductRestaurantsDTO;
import ge.mimino.travel.model.*;
import ge.mimino.travel.request.AddRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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

  public int removeTouristCounts(Integer requestId) {
    return entityManager.createQuery("delete from " + TouristCount.class.getSimpleName() + " c where c.requestId=" + requestId).executeUpdate();
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
    if (srchRequest.getTourCode() != null) {
      q.append(" and e.tourCode like '%").append(srchRequest.getTourCode()).append("%'");
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

  public void removeProductHotels(Integer requestId, Integer day) {
    Query query = getEntityManager().createQuery("delete from " + ProductHotels.class.getSimpleName()
            + " c where c.requestId=" + requestId + " and c.day=" + day);
    query.executeUpdate();
  }

  public void removeProductNonstandarts(Integer requestId, List<Integer> nonstandarts, Integer day) {
    Query query = getEntityManager().createQuery("delete from " + ProductNonstandarts.class.getSimpleName()
            + " c where c.requestId=" + requestId + " and c.day=" + day + " and c.nonstandartServiceId in :listOfIds");
    query.setParameter("listOfIds", nonstandarts);
    query.executeUpdate();
  }

  public void removeProductPlaces(Integer requestId, List<Integer> places, Integer day) {
    Query query = getEntityManager().createQuery("delete from " + ProductPlaces.class.getSimpleName()
            + " c where c.requestId=" + requestId + " and c.day=" + day + " and c.placeId in :listOfIds");
    query.setParameter("listOfIds", places);
    query.executeUpdate();
  }

  public void removeProductRegions(Integer requestId, List<Integer> regions, Integer day) {
    Query query = getEntityManager().createQuery("delete from " + ProductRegions.class.getSimpleName()
            + " c where c.requestId=" + requestId + " and c.day=" + day + " and c.regionId in :listOfIds");
    query.setParameter("listOfIds", regions);
    query.executeUpdate();
  }

  public void removeProductSights(Integer requestId, List<GeoObjectDTO> sights, Integer day) {
    List<Integer> sightsIds = new ArrayList<>();
    for (GeoObjectDTO rest : sights) {
      sightsIds.add(rest.getId());
    }
    Query query = getEntityManager().createQuery("delete from " + ProductSights.class.getSimpleName()
            + " c where c.requestId=" + requestId + " and c.day=" + day + " and c.sightId in :listOfIds");
    query.setParameter("listOfIds", sightsIds);
    query.executeUpdate();
  }

  public void removeProductTransports(Integer requestId, List<Integer> transports, Integer day) {
    Query query = getEntityManager().createQuery("delete from " + ProductTransports.class.getSimpleName()
            + " c where c.requestId=" + requestId + " and c.day=" + day + " and c.transportId in :listOfIds");
    query.setParameter("listOfIds", transports);
    query.executeUpdate();
  }

  public void removeProductRestaurants(Integer requestId, List<ProductRestaurantsDTO> restaurants, Integer day) {
    List<Integer> restaurantIds = new ArrayList<>();
    for (ProductRestaurantsDTO rest : restaurants) {
      restaurantIds.add(rest.getRestaurantId());
    }
    Query query = getEntityManager().createQuery("delete from " + ProductRestaurants.class.getSimpleName()
            + " c where c.requestId=" + requestId + " and c.day=" + day + " and c.restaurantId in :listOfIds");
    query.setParameter("listOfIds", restaurantIds);
    query.executeUpdate();
  }
}
