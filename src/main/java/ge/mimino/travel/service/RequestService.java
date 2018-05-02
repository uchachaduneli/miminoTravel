package ge.mimino.travel.service;


import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dao.RequestDAO;
import ge.mimino.travel.dto.DetailsDTO;
import ge.mimino.travel.dto.RequestCountryDTO;
import ge.mimino.travel.dto.RequestDTO;
import ge.mimino.travel.dto.RequestDetailsDTO;
import ge.mimino.travel.model.Details;
import ge.mimino.travel.model.Request;
import ge.mimino.travel.model.RequestDetails;
import ge.mimino.travel.request.AddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Request obj = new Request();
//        obj.setContactEmail(request.getContactEmail());
//        obj.setCombined(request.getCombined());
//        obj.setTourStart(request.getTourStart());
//        obj.setTourEnd(request.getTourEnd());
//        obj.setDaysCount(request.getDaysCount());
//        obj.setNightsCount(request.getNightsCount());
//        obj.setTouristsCount(request.getTouristsCount());
//        obj.setTouristsCountNote(request.getTouristsCountNote());
//        obj.setArrivalTime(new Timestamp(request.getArrivalTime().getTime()));//request.getArrivalTime()
//        obj.setLeaveTime(new Timestamp(request.getLeaveTime().getTime()));//request.getLeaveTime()
//        obj.setTourType(request.getTourType());
//        obj.setGuideDriver(request.getGuideDriver());
//        obj.setHotelCategory(request.getHotelCategory());
//        obj.setEntranceFees(request.getEntranceFees());
//        obj.setComment(request.getComment());
//        obj.setBudget(request.getBudget());
//
//        obj.setCurrency((Currency) caseDAO.find(Currency.class, request.getCurrencyId()));
//        obj.setArrivalCity((City) caseDAO.find(City.class, request.getArrivalCityId()));
//        obj.setLeaveCity((City) caseDAO.find(City.class, request.getLeaveCityId()));
//        obj.setMealCategory((MealCategory) caseDAO.find(MealCategory.class, request.getMealCategoryId()));
//        obj.setPackageCategory((PackageCategory) caseDAO.find(PackageCategory.class, request.getPackageCategoryId()));
//
//        if (request.getId() != null) {
//            obj.setId(request.getId());
//            obj = (Case) caseDAO.update(obj);
//        } else {
//            obj = (Case) caseDAO.create(obj);
//        }
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
        return RequestCountryDTO.parseToList(requestDAO.getAllByParamValue(RequestDetails.class, paramValues, null));
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