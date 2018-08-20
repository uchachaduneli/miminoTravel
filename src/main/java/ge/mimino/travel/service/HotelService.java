package ge.mimino.travel.service;


import ge.mimino.travel.dao.HotelDAO;
import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dto.HotelDTO;
import ge.mimino.travel.dto.HotelImagesDTO;
import ge.mimino.travel.model.Hotel;
import ge.mimino.travel.model.HotelImages;
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
public class HotelService {

    @Autowired
    private HotelDAO hotelDAO;


    public List<HotelDTO> getHotels(int start, int limit, HotelDTO srchRequest) {
        return HotelDTO.parseToList(hotelDAO.getHotels(start, limit, srchRequest));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Hotel save(HotelDTO request) throws Exception {

        Hotel obj = request.getId() != null ? ((Hotel) hotelDAO.find(Hotel.class, request.getId())) : new Hotel();
        obj.setDoublePrice(request.getDoublePrice());
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
        obj.setSinglePrice(request.getSinglePrice());
        obj.setTriplePrice(request.getTriplePrice());
        obj.setSingleSupply(request.getSingleSupply());
        obj.setStarsCount(request.getStarsCount());
        obj.setLink(request.getLink());
        obj.setPlace((Place) hotelDAO.find(Place.class, request.getPlaceId()));

        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Hotel) hotelDAO.update(obj);
        } else {
            obj = (Hotel) hotelDAO.create(obj);
        }

        return obj;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        Hotel obj = (Hotel) hotelDAO.find(Hotel.class, id);
        if (obj != null) {
            hotelDAO.delete(obj);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveImages(List<String> images, Integer id) {
        hotelDAO.removeImages(id);
        if (images != null && !images.isEmpty()) {
            for (String img : images) {
                hotelDAO.create(new HotelImages(img, id));
            }
        }
    }

    public List<HotelImagesDTO> getHotelImages(Integer hotelId) {
        List<ParamValuePair> criteria = new ArrayList<>();
        criteria.add(new ParamValuePair("hotelId", hotelId));
        return HotelImagesDTO.parseToList(hotelDAO.getAllByParamValue(HotelImages.class, criteria, null));
    }
}