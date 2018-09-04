package ge.mimino.travel.service;


import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dao.RequestDAO;
import ge.mimino.travel.dto.*;
import ge.mimino.travel.model.*;
import ge.mimino.travel.model.Currency;
import ge.mimino.travel.request.AddRequest;
import ge.mimino.travel.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ucha
 */
@Service
public class RequestService {

    @Autowired
    private RequestDAO requestDAO;


    public List<RequestDTO> getRequests(int start, int limit, AddRequest srchRequest) {
        return RequestDTO.parseToList(requestDAO.getRequests(start, limit, srchRequest));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Request save(AddRequest request) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Request obj = new Request();
        obj.setContactEmail(request.getContactEmail());
        obj.setCombined(request.getCombined());
        obj.setTourStart(request.getTourStart());
        obj.setStrTourStart(request.getStrTourStart());
        obj.setTourEnd(request.getTourEnd());
        obj.setStrTourEnd(request.getStrTourEnd());
        obj.setDaysCount(request.getDaysCount());
        obj.setNightsCount(request.getNightsCount());
        obj.setTouristsCount(request.getTouristsCount());
        obj.setTouristsCountNote(request.getTouristsCountNote());
        obj.setArrivalTime(new Timestamp(sdf.parse(request.getArrivalTime()).getTime()));//request.getArrivalTime()
        obj.setStrArrivalTime(request.getStrArrivalTime());
        obj.setLeaveTime(new Timestamp(sdf.parse(request.getLeaveTime()).getTime()));//request.getLeaveTime()
        obj.setStrLeaveTime(request.getStrLeaveTime());
        obj.setTourType(request.getTourType());
        obj.setGuideDriver(request.getGuideDriver());
        obj.setHotelCategory(request.getHotelCategory());
        obj.setEntranceFees(request.getEntranceFees());
        obj.setComment(request.getComment());
        obj.setBudget(request.getBudget());
        obj.setTourCode(request.getTourCode());
        obj.setNationality(request.getNationality());
//
        obj.setCurrency((Currency) requestDAO.find(Currency.class, request.getCurrencyId()));
        obj.setArrivalCity((City) requestDAO.find(City.class, request.getArrivalCityId()));
        obj.setLeaveCity((City) requestDAO.find(City.class, request.getLeaveCityId()));
        obj.setMealCategory((MealCategory) requestDAO.find(MealCategory.class, request.getMealCategoryId()));
        obj.setPackageCategory((PackageCategory) requestDAO.find(PackageCategory.class, request.getPackageCategoryId()));
        obj.setGuideLanguage((Language) requestDAO.find(Language.class, request.getGuideLanguageId()));
        obj.setUser((Users) requestDAO.find(Users.class, request.getUserId()));
//
        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Request) requestDAO.update(obj);
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            obj.setTourCode(cal.get(Calendar.DAY_OF_MONTH) + "." + cal.get(Calendar.MONTH) + "-" + request.getTouristsCount());
            obj.setRequestKey(UUID.randomUUID() + "");
            obj = (Request) requestDAO.create(obj);
        }

        requestDAO.removeRequestCountries(obj.getId());
        if (request.getCombinedCountries() != null && !request.getCombinedCountries().isEmpty()) {
            for (CombinedCountry combCntr : request.getCombinedCountries()) {
                requestDAO.create(new RequestCountry(obj.getId(), ((Country) requestDAO.find(Country.class, combCntr.getCountryId())),
                        combCntr.getDaysCount(), combCntr.getNote()));
            }
        }

        return obj;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveMessage(RequestMessageDTO request) throws Exception {
        RequestMessage msg = new RequestMessage();
        msg.setRequestId(request.getRequestId());
        msg.setMessage(request.getMessage());
        msg.setUser((Users) requestDAO.find(Users.class, request.getUserId()));
        requestDAO.create(msg);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveProduct(ProductRequest request) throws Exception {

        if (request.getHotels() != null && !request.getHotels().isEmpty()) {
            requestDAO.removeProductHotels(request.getRequestId(), request.getHotels(), request.getDay());
            for (Integer obj : request.getHotels()) {
                requestDAO.create(new ProductHotels(obj, request.getRequestId(), request.getDay()));
            }
        }

        if (request.getNonstandarts() != null && !request.getNonstandarts().isEmpty()) {
            requestDAO.removeProductNonstandarts(request.getRequestId(), request.getNonstandarts(), request.getDay());
            for (Integer obj : request.getNonstandarts()) {
                requestDAO.create(new ProductNonstandarts(obj, request.getRequestId(), request.getDay()));
            }
        }

        if (request.getPlaces() != null && !request.getPlaces().isEmpty()) {
            requestDAO.removeProductPlaces(request.getRequestId(), request.getPlaces(), request.getDay());
            for (Integer obj : request.getPlaces()) {
                requestDAO.create(new ProductPlaces(obj, request.getRequestId(), request.getDay()));
            }
        }

        if (request.getRegions() != null && !request.getRegions().isEmpty()) {
            requestDAO.removeProductRegions(request.getRequestId(), request.getRegions(), request.getDay());
            for (Integer obj : request.getRegions()) {
                requestDAO.create(new ProductRegions(obj, request.getRequestId(), request.getDay()));
            }
        }

        if (request.getSights() != null && !request.getSights().isEmpty()) {
            requestDAO.removeProductSights(request.getRequestId(), request.getSights(), request.getDay());
            for (Integer obj : request.getSights()) {
                requestDAO.create(new ProductSights(obj, request.getRequestId(), request.getDay()));
            }
        }

        if (request.getTransports() != null && !request.getTransports().isEmpty()) {
            requestDAO.removeProductTransports(request.getRequestId(), request.getTransports(), request.getDay());
            for (Integer obj : request.getTransports()) {
                requestDAO.create(new ProductTransports(obj, request.getRequestId(), request.getDay()));
            }
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        Request obj = (Request) requestDAO.find(Request.class, id);
        if (obj != null) {
            requestDAO.delete(obj);
        }
    }

    public List<RequestCountryDTO> getRequestCountries(int caseId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", caseId));
        return RequestCountryDTO.parseToList(requestDAO.getAllByParamValue(RequestCountry.class, paramValues, null));
    }

    public List<RequestDetailsDTO> getRequestDetails(int caseId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", caseId));
        return RequestDetailsDTO.parseToList(requestDAO.getAllByParamValue(RequestDetails.class, paramValues, null));
    }

    public List<RequestMessageDTO> getRequestMessages(int id) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", id));
        return RequestMessageDTO.parseToList(requestDAO.getAllByParamValue(RequestMessage.class, paramValues, null));
    }

    public List<DetailsDTO> getDetails() {
        return DetailsDTO.parseToList(requestDAO.getAll(Details.class));
    }

    public Request getRequestByKey(String key) throws IndexOutOfBoundsException {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestKey", key));
        return (Request) requestDAO.getAllByParamValue(Request.class, paramValues, null).get(0);
    }

    public ProductRequest getProductDetailsById(Integer requestId, Integer day) {

        ProductRequest res = new ProductRequest();
        res.setHotels(new ArrayList<>());
        res.setNonstandarts(new ArrayList<>());
        res.setPlaces(new ArrayList<>());
        res.setRegions(new ArrayList<>());
        res.setSights(new ArrayList<>());
        res.setTransports(new ArrayList<>());

        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", requestId));
        paramValues.add(new ParamValuePair("day", day));

        for (ProductHotels obj : (List<ProductHotels>) requestDAO.getAllByParamValue(ProductHotels.class, paramValues, null)) {
            res.getHotels().add(obj.getHotelId());
        }

        for (ProductNonstandarts obj : (List<ProductNonstandarts>) requestDAO.getAllByParamValue(ProductNonstandarts.class, paramValues, null)) {
            res.getNonstandarts().add(obj.getNonstandartServiceId());
        }

        for (ProductPlaces obj : (List<ProductPlaces>) requestDAO.getAllByParamValue(ProductPlaces.class, paramValues, null)) {
            res.getPlaces().add(obj.getPlaceId());
        }

        for (ProductSights obj : (List<ProductSights>) requestDAO.getAllByParamValue(ProductSights.class, paramValues, null)) {
            res.getSights().add(obj.getSightId());
        }

        for (ProductTransports obj : (List<ProductTransports>) requestDAO.getAllByParamValue(ProductTransports.class, paramValues, null)) {
            res.getTransports().add(obj.getTransportId());
        }

        for (ProductRegions obj : (List<ProductRegions>) requestDAO.getAllByParamValue(ProductRegions.class, paramValues, null)) {
            res.getRegions().add(obj.getRegionId());
        }

        return res;
    }
}