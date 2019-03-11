package ge.mimino.travel.service;


import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dao.RestaurantDAO;
import ge.mimino.travel.dto.RestaurantDTO;
import ge.mimino.travel.dto.RestaurantMealCatsDTO;
import ge.mimino.travel.dto.RestaurantPackageDTO;
import ge.mimino.travel.model.Place;
import ge.mimino.travel.model.Restaurant;
import ge.mimino.travel.model.RestaurantMealCategories;
import ge.mimino.travel.model.RestaurantPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ucha
 */
@Service
public class RestaurantService {

    @Autowired
    private RestaurantDAO restaurantDAO;


    public List<RestaurantDTO> getRestaurants(int start, int limit, RestaurantDTO srchRequest) {
        return RestaurantDTO.parseToList(restaurantDAO.getRestaurants(start, limit, srchRequest));
    }

    public List<RestaurantDTO> getRestaurantsByPlaces(List<Integer> placeIds) {
        return RestaurantDTO.parseToList(restaurantDAO.getRestaurantsByPlaces(placeIds));
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveMealCats(List<RestaurantMealCatsDTO> request, Integer restaurantId) throws Exception {

        restaurantDAO.removeRestaurantMealCats(restaurantId);
        if (request != null && !request.isEmpty()) {
            for (RestaurantMealCatsDTO pack : request) {
                restaurantDAO.create(new RestaurantMealCategories(restaurantId, pack.getCategoryName(), pack.getPrice(), pack.getGroup()));
            }
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public Restaurant save(RestaurantDTO request) throws Exception {

        Restaurant obj = request.getId() != null ? ((Restaurant) restaurantDAO.find(Restaurant.class, request.getId())) : new Restaurant();
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
        obj.setPlace((Place) restaurantDAO.find(Place.class, request.getPlaceId()));

        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Restaurant) restaurantDAO.update(obj);
        } else {
            obj = (Restaurant) restaurantDAO.create(obj);
        }

        restaurantDAO.removeRestaurantPackages(obj.getId());
        if (request.getRestaurantPackages() != null && !request.getRestaurantPackages().isEmpty()) {
            for (String pack : request.getRestaurantPackages()) {
                restaurantDAO.create(new RestaurantPackage(obj.getId(), pack));
            }
        }

        return obj;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        Restaurant obj = (Restaurant) restaurantDAO.find(Restaurant.class, id);
        if (obj != null) {
            restaurantDAO.delete(obj);
        }
    }

    public List<RestaurantPackageDTO> getRestaurantPackages(int restaurantId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("restaurantId", restaurantId));
        return RestaurantPackageDTO.parseToList(restaurantDAO.getAllByParamValue(RestaurantPackage.class, paramValues, null));
    }

    public List<RestaurantMealCatsDTO> getRestaurantMealCats(int restaurantId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("restaurantId", restaurantId));
        return RestaurantMealCatsDTO.parseToList(restaurantDAO.getAllByParamValue(RestaurantMealCategories.class, paramValues, null));
    }

}