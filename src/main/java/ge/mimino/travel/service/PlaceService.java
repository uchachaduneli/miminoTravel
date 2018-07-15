package ge.mimino.travel.service;


import ge.mimino.travel.dao.PlaceDAO;
import ge.mimino.travel.dto.PlaceDTO;
import ge.mimino.travel.model.Language;
import ge.mimino.travel.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Throwable.class)
    public Place save(PlaceDTO request) throws Exception {

        Place obj = request.getId() != null ? ((Place) placeDAO.find(Place.class, request.getId())) : new Place();
        obj.setLanguage((Language) placeDAO.find(Language.class, request.getLanguageId()));
        obj.setDescription(request.getDescription());
        obj.setName(request.getName());

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
}