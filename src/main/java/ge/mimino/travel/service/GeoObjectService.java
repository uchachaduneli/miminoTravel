package ge.mimino.travel.service;


import ge.mimino.travel.dao.GeoObjectDAO;
import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dto.GeoObjectDTO;
import ge.mimino.travel.dto.GeoObjectImagesDTO;
import ge.mimino.travel.dto.GeoObjectTypesDTO;
import ge.mimino.travel.model.GeoObject;
import ge.mimino.travel.model.GeoObjectImages;
import ge.mimino.travel.model.GeoObjectTypes;
import ge.mimino.travel.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<GeoObjectDTO> getGeoObjectsByPlaces(List<Integer> placeIds) {
        return GeoObjectDTO.parseToList(geoObjectDAO.getGeoObjectsByPlaces(placeIds));
    }

    @Transactional(rollbackFor = Throwable.class)
    public GeoObject save(GeoObjectDTO request) throws Exception {

        GeoObject obj = request.getId() != null ? ((GeoObject) geoObjectDAO.find(GeoObject.class, request.getId())) : new GeoObject();
        obj.setPersonPrice(request.getPersonPrice());
        obj.setType((GeoObjectTypes) geoObjectDAO.find(GeoObjectTypes.class, request.getTypeId()));
        obj.setNameEn(request.getNameEn());
        obj.setNameGe(request.getNameGe());
        obj.setNameFr(request.getNameFr());
        obj.setNameIt(request.getNameIt());
        obj.setNameSp(request.getNameSp());
        obj.setNamePo(request.getNamePo());
        obj.setNameRu(request.getNameRu());
        obj.setDescriptionEn(request.getDescriptionEn());
        obj.setDescriptionGe(request.getDescriptionGe());
        obj.setDescriptionFr(request.getDescriptionFr());
        obj.setDescriptionIt(request.getDescriptionIt());
        obj.setDescriptionSp(request.getDescriptionSp());
        obj.setDescriptionPo(request.getDescriptionPo());
        obj.setDescriptionRu(request.getDescriptionRu());
        obj.setPlace((Place) geoObjectDAO.find(Place.class, request.getPlaceId()));

        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (GeoObject) geoObjectDAO.update(obj);
        } else {
            obj = (GeoObject) geoObjectDAO.create(obj);
        }

        geoObjectDAO.removeImages(obj.getId());
        if (request.getImages() != null && !request.getImages().isEmpty()) {
            for (String img : request.getImages()) {
                geoObjectDAO.create(new GeoObjectImages(img.contains(obj.getId() + "") ? img : obj.getId() + "_" + img, obj.getId()));
            }
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

    public List<GeoObjectImagesDTO> getGeoObjectImages(Integer id) {
        List<ParamValuePair> criteria = new ArrayList<>();
        ParamValuePair crit = new ParamValuePair("geoObjectId", id);
        criteria.add(crit);
        return GeoObjectImagesDTO.parseToList(geoObjectDAO.getAllByParamValue(GeoObjectImages.class, criteria, null));
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveImages(List<String> images, Integer id) {
        geoObjectDAO.removeImages(id);
        if (images != null && !images.isEmpty()) {
            for (String img : images) {
                geoObjectDAO.create(new GeoObjectImages(img, id));
            }
        }
    }
}