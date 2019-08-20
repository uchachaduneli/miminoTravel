package ge.mimino.travel.service;


import ge.mimino.travel.dao.HotelDAO;
import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dto.HotelDTO;
import ge.mimino.travel.dto.HotelImagesDTO;
import ge.mimino.travel.dto.HotelPricesDTO;
import ge.mimino.travel.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author ucha
 */
@Service
public class HotelService {
    Logger logger = Logger.getLogger(HotelService.class);

    @Autowired
    private HotelDAO hotelDAO;


    public List<HotelDTO> getHotels(int start, int limit, HotelDTO srchRequest) {
        return HotelDTO.parseToList(hotelDAO.getHotels(start, limit, srchRequest));
    }

    public List<HotelDTO> getHotelsByPlaces(List<Integer> placeIds, String stars) {
        return HotelDTO.parseToList(hotelDAO.getHotelsByPlaces(placeIds, stars));
    }

    public List<HotelPricesDTO> getHotelPrices(Integer hotelId, Date fromDate, Date toDate) {
        return HotelPricesDTO.parseToList(hotelDAO.getHotelPraces(hotelId, fromDate, toDate));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Hotel save(HotelDTO request) throws Exception {

        Hotel obj = request.getId() != null ? ((Hotel) hotelDAO.find(Hotel.class, request.getId())) : new Hotel();
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
        obj.setStarsCount(request.getStarsCount());
        obj.setLink(request.getLink());
        obj.setCurrency(request.getCurrency());
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
    public void savePrices(HotelPricesDTO request) throws Exception {
        HotelPriceDateRanges priceDateRanges = new HotelPriceDateRanges(request.getFrom(), request.getTo());
        priceDateRanges = (HotelPriceDateRanges) hotelDAO.create(priceDateRanges);
        Hotel hotel = (Hotel) hotelDAO.find(Hotel.class, request.getHotelId());
        for (Date dt : getDatesBetween(request.getFrom(), request.getTo())) {
            HotelPrices obj = new HotelPrices();
            obj.setHotel(hotel);
            obj.setDate(dt);
            obj.setSingleFit(request.getSingleFit());
            obj.setSingleGroup(request.getSingleGroup());
            obj.setDoubleFit(request.getDoubleFit());
            obj.setDoubleGroup(request.getDoubleGroup());
            obj.setTripleFit(request.getTripleFit());
            obj.setTripleGroup(request.getTripleGroup());
            obj.setSingleSupplementFit(request.getSingleSupplementFit());
            obj.setSingleSupplementGroup(request.getSingleSupplementGroup());
            obj.setDateRange(priceDateRanges);
            hotelDAO.create(obj);
        }
    }

    public List<Date> getDatesBetween(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);
        endCalendar.add(Calendar.DATE, 1);

        while (calendar.before(endCalendar)) {
            Date result = new java.sql.Date(calendar.getTime().getTime());
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
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