package ge.mimino.travel.dao;


import ge.mimino.travel.dto.HotelDTO;
import ge.mimino.travel.model.Hotel;
import ge.mimino.travel.model.HotelImages;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ME.
 */

@Repository
public class HotelDAO extends AbstractDAO {

    @PersistenceContext(unitName = "mimino")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


    public List<Hotel> getHotels(int start, int limit, HotelDTO srchRequest) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Hotel.class.getSimpleName()).append(" e Where 1=1 ");

        if (srchRequest.getId() != null && srchRequest.getId() > 0) {
            q.append(" and e.id ='").append(srchRequest.getId()).append("'");
        }
        if (srchRequest.getStarsCount() != null && srchRequest.getStarsCount().length() > 0) {
            q.append(" and e.starsCount ='").append(srchRequest.getStarsCount()).append("'");
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

        TypedQuery<Hotel> query = entityManager.createQuery(q.toString(), Hotel.class);
        return query.setFirstResult(start).setMaxResults(limit).getResultList();
    }

    public int removeImages(Integer id) {
        return entityManager.createQuery("delete from " + HotelImages.class.getSimpleName() + " c where c.hotelId=" + id).executeUpdate();
    }

    public List<Hotel> getHotelsByPlaces(List<Integer> placeIds, String stars) {
        String query = "select c from " + Hotel.class.getSimpleName() + " c where 1=1 ";
        if (!placeIds.isEmpty()) {
            query += "and c.place.id in :listOfIds ";
        }
        if (stars != null && stars.length() > 0) {
            query += " and c.starsCount = " + stars;
        }
        TypedQuery<Hotel> tquery = getEntityManager().createQuery(query, Hotel.class);
        tquery.setParameter("listOfIds", placeIds);
        return tquery.getResultList();
    }
}
