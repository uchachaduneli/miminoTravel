package ge.mimino.travel.service;


import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dao.PlaceDAO;
import ge.mimino.travel.dto.PlaceDTO;
import ge.mimino.travel.dto.PlaceImagesDTO;
import ge.mimino.travel.model.Place;
import ge.mimino.travel.model.PlaceImages;
import ge.mimino.travel.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ucha
 */
@Service
public class PlaceService {

    @Autowired
    private PlaceDAO placeDAO;


    public List<PlaceDTO> getPlaces(int start, int limit, PlaceDTO srchRequest) {
        return PlaceDTO.parseToList(placeDAO.getPlaces(start, limit, srchRequest));
    }

    public List<PlaceDTO> getPlacesByRegion(List<Integer> regionIds) {
        return PlaceDTO.parseToList(placeDAO.getPlacesByRegion(regionIds));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Place save(PlaceDTO request) throws Exception {

        Place obj = request.getId() != null ? ((Place) placeDAO.find(Place.class, request.getId())) : new Place();
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
        obj.setRegion((Region) placeDAO.find(Region.class, request.getRegionId()));

        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Place) placeDAO.update(obj);
        } else {
            obj = (Place) placeDAO.create(obj);
        }

        return obj;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        Place obj = (Place) placeDAO.find(Place.class, id);
        if (obj != null) {
            placeDAO.delete(obj);
        }
    }

    public List<PlaceImagesDTO> getObjectImages(Integer id) {
        List<ParamValuePair> criteria = new ArrayList<>();
        ParamValuePair crit = new ParamValuePair("placeId", id);
        criteria.add(crit);
        return PlaceImagesDTO.parseToList(placeDAO.getAllByParamValue(PlaceImages.class, criteria, null));
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveImages(List<String> images, Integer id) {
        placeDAO.removeImages(id);
        if (images != null && !images.isEmpty()) {
            for (String img : images) {
                placeDAO.create(new PlaceImages(img, id));
            }
        }
    }
}