package ge.mimino.travel.dao;


import ge.mimino.travel.dto.GeoObjectDTO;
import ge.mimino.travel.model.GeoObject;
import ge.mimino.travel.model.GeoObjectImages;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ME.
 */

@Repository
public class GeoObjectDAO extends AbstractDAO {

    @PersistenceContext(unitName = "mimino")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


    public List<GeoObject> getGeoObjects(int start, int limit, GeoObjectDTO srchRequest) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(GeoObject.class.getSimpleName()).append(" e Where 1=1 ");

        if (srchRequest.getId() != null && srchRequest.getId() > 0) {
            q.append(" and e.id ='").append(srchRequest.getId()).append("'");
        }
        if (srchRequest.getTypeId() != null && srchRequest.getTypeId() > 0) {
            q.append(" and e.type.id ='").append(srchRequest.getTypeId()).append("'");
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

        TypedQuery<GeoObject> query = entityManager.createQuery(q.toString(), GeoObject.class);
        return query.setFirstResult(start).setMaxResults(limit).getResultList();
    }

    public int removeImages(Integer id) {
        return entityManager.createQuery("delete from " + GeoObjectImages.class.getSimpleName() + " c where c.geoObjectId=" + id).executeUpdate();
    }
}
