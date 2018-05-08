package ge.mimino.travel.service;


import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dao.RequestDAO;
import ge.mimino.travel.dto.*;
import ge.mimino.travel.model.*;
import ge.mimino.travel.request.AddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ucha
 */
@Service
public class RequestService {

    @Autowired
    private RequestDAO requestDAO;


    public List<RequestDTO> getRequests(int start, int limit, AddRequest srchRequest) {
        return RequestDTO.parseToList(requestDAO.getAll(Request.class));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Request save(AddRequest request) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Request obj = new Request();
        obj.setContactEmail(request.getContactEmail());
        obj.setCombined(request.getCombined());
        obj.setTourStart(request.getTourStart());
        obj.setTourEnd(request.getTourEnd());
        obj.setDaysCount(request.getDaysCount());
        obj.setNightsCount(request.getNightsCount());
        obj.setTouristsCount(request.getTouristsCount());
        obj.setTouristsCountNote(request.getTouristsCountNote());
        obj.setArrivalTime(new Timestamp(sdf.parse(request.getArrivalTime()).getTime()));//request.getArrivalTime()
        obj.setLeaveTime(new Timestamp(sdf.parse(request.getLeaveTime()).getTime()));//request.getLeaveTime()
        obj.setTourType(request.getTourType());
        obj.setGuideDriver(request.getGuideDriver());
        obj.setHotelCategory(request.getHotelCategory());
        obj.setEntranceFees(request.getEntranceFees());
        obj.setComment(request.getComment());
        obj.setBudget(request.getBudget());
//
        obj.setCurrency((Currency) requestDAO.find(Currency.class, request.getCurrencyId()));
        obj.setArrivalCity((City) requestDAO.find(City.class, request.getArrivalCityId()));
        obj.setLeaveCity((City) requestDAO.find(City.class, request.getLeaveCityId()));
        obj.setMealCategory((MealCategory) requestDAO.find(MealCategory.class, request.getMealCategoryId()));
        obj.setPackageCategory((PackageCategory) requestDAO.find(PackageCategory.class, request.getPackageCategoryId()));
        obj.setGuideLanguage((Language) requestDAO.find(Language.class, request.getGuideLanguageId()));
//
        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Request) requestDAO.update(obj);
        } else {
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

    public List<DetailsDTO> getDetails() {
        return DetailsDTO.parseToList(requestDAO.getAll(Details.class));
    }
}