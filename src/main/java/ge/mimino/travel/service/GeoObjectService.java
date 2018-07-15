package ge.mimino.travel.service;


import ge.mimino.travel.dao.GeoObjectDAO;
import ge.mimino.travel.dto.GeoObjectDTO;
import ge.mimino.travel.dto.GeoObjectImagesDTO;
import ge.mimino.travel.dto.GeoObjectTypesDTO;
import ge.mimino.travel.model.GeoObject;
import ge.mimino.travel.model.GeoObjectImages;
import ge.mimino.travel.model.GeoObjectTypes;
import ge.mimino.travel.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class GeoObjectService {

    @Autowired
    private GeoObjectDAO geoObjectDAO;


    public List<GeoObjectDTO> getGeoObjects(int start, int limit, GeoObjectDTO srchRequest) {
        return GeoObjectDTO.parseToList(geoObjectDAO.getGeoObjects(start, limit, srchRequest));
    }

    @Transactional(rollbackFor = Throwable.class)
    public GeoObject save(GeoObjectDTO request) throws Exception {

        GeoObject obj = request.getId() != null ? ((GeoObject) geoObjectDAO.find(GeoObject.class, request.getId())) : new GeoObject();
        obj.setLanguage((Language) geoObjectDAO.find(Language.class, request.getLanguageId()));
        obj.setPersonPrice(request.getPersonPrice());
        obj.setType((GeoObjectTypes) geoObjectDAO.find(GeoObjectTypes.class, request.getTypeId()));
        obj.setDescription(request.getDescription());
        obj.setName(request.getName());

        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (GeoObject) geoObjectDAO.update(obj);
        } else {
            obj = (GeoObject) geoObjectDAO.create(obj);
        }

        return obj;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        GeoObject obj = (GeoObject) geoObjectDAO.find(GeoObject.class, id);
        if (obj != null) {
            geoObjectDAO.delete(obj);
        }
    }


    public List<GeoObjectTypesDTO> getGeoObjectTypes() {
        return GeoObjectTypesDTO.parseToList(geoObjectDAO.getAll(GeoObjectTypes.class));
    }

    public List<GeoObjectImagesDTO> getGeoObjectImages() {
        return GeoObjectImagesDTO.parseToList(geoObjectDAO.getAll(GeoObjectImages.class));
    }
}