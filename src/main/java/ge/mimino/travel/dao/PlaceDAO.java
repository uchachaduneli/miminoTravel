package ge.mimino.travel.dao;


import ge.mimino.travel.dto.PlaceDTO;
import ge.mimino.travel.model.Place;
import ge.mimino.travel.model.PlaceImages;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ME.
 */

@Repository
public class PlaceDAO extends AbstractDAO {

    @PersistenceContext(unitName = "mimino")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


    public List<Place> getPlaces(int start, int limit, PlaceDTO srchRequest) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Place.class.getSimpleName()).append(" e Where 1=1 ");

        if (srchRequest.getId() != null && srchRequest.getId() > 0) {
            q.append(" and e.id ='").append(srchRequest.getId()).append("'");
        }
        if (srchRequest.getRegionId() != null && srchRequest.getRegionId() > 0) {
            q.append(" and e.region.id ='").append(srchRequest.getRegionId()).append("'");
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

        TypedQuery<Place> query = entityManager.createQuery(q.toString(), Place.class);
        return query.setFirstResult(start).setMaxResults(limit).getResultList();
    }

    public int removeImages(Integer id) {
        return entityManager.createQuery("delete from " + PlaceImages.class.getSimpleName() + " c where c.placeId=" + id).executeUpdate();
    }

    public List<Place> getPlacesByRegion(List<Integer> regionIds) {
        TypedQuery<Place> query = getEntityManager().createQuery("select c from " + Place.class.getSimpleName()
                + " c where c.region.id in :listOfIds", Place.class);
        query.setParameter("listOfIds", regionIds);
        return query.getResultList();
    }
}
