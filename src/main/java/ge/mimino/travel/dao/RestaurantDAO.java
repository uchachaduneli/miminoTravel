package ge.mimino.travel.dao;


import ge.mimino.travel.dto.RestaurantDTO;
import ge.mimino.travel.model.Restaurant;
import ge.mimino.travel.model.RestaurantMealCategories;
import ge.mimino.travel.model.RestaurantPackage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ME.
 */

@Repository
public class RestaurantDAO extends AbstractDAO {

    @PersistenceContext(unitName = "mimino")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


    public List<Restaurant> getRestaurants(int start, int limit, RestaurantDTO srchRequest) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Restaurant.class.getSimpleName()).append(" e Where 1=1 ");

        if (srchRequest.getId() != null && srchRequest.getId() > 0) {
            q.append(" and e.id ='").append(srchRequest.getId()).append("'");
        }
        if (srchRequest.getPlaceId() != null && srchRequest.getPlaceId() > 0) {
            q.append(" and e.place.id ='").append(srchRequest.getPlaceId()).append("'");
        }
        if (srchRequest.getNameEn() != null) {
            q.append(" and e.nameEn like '%").append(srchRequest.getNameEn()).append("%'");
        }
        if (srchRequest.getNameGe() != null) {
            q.append(" and e.nameGe like '%").append(srchRequest.getNameGe()).append("%'");
        }
        if (srchRequest.getNameFr() != null) {
            q.append(" and e.nameFr like '%").append(srchRequest.getNameFr()).append("%'");
        }
        if (srchRequest.getNameIt() != null) {
            q.append(" and e.nameIt like '%").append(srchRequest.getNameIt()).append("%'");
        }
        if (srchRequest.getNameSp() != null) {
            q.append(" and e.nameSp like '%").append(srchRequest.getNameSp()).append("%'");
        }
        if (srchRequest.getNamePo() != null) {
            q.append(" and e.namePo like '%").append(srchRequest.getNamePo()).append("%'");
        }
        if (srchRequest.getNameRu() != null) {
            q.append(" and e.nameRu like '%").append(srchRequest.getNameRu()).append("%'");
        }

        TypedQuery<Restaurant> query = entityManager.createQuery(q.toString(), Restaurant.class);
        return query.setFirstResult(start).setMaxResults(limit).getResultList();
    }

    public int removeRestaurantPackages(Integer restaurantId) {
        return entityManager.createQuery("delete from " + RestaurantPackage.class.getSimpleName()
                + " c where c.restaurantId=" + restaurantId).executeUpdate();
    }

    public int removeRestaurantMealCats(Integer restaurantId) {
        return entityManager.createQuery("delete from " + RestaurantMealCategories.class.getSimpleName()
                + " c where c.restaurantId=" + restaurantId).executeUpdate();
    }

    public List<Restaurant> getRestaurantsByPlaces(List<Integer> placeIds) {
        String qr = "select c from " + Restaurant.class.getSimpleName() + " c";
        TypedQuery<Restaurant> query;
        if (!placeIds.isEmpty()) {
            qr += " where c.place.id in :listOfIds";
            query = getEntityManager().createQuery(qr, Restaurant.class);
            query.setParameter("listOfIds", placeIds);
        } else {
            query = getEntityManager().createQuery(qr, Restaurant.class);
        }
        return query.getResultList();
    }
}
