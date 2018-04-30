package ge.mimino.travel.service;


import ge.mimino.travel.dao.CaseDAO;
import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dto.CaseCountryDTO;
import ge.mimino.travel.dto.CaseDTO;
import ge.mimino.travel.dto.CaseDetailsDTO;
import ge.mimino.travel.dto.DetailsDTO;
import ge.mimino.travel.model.*;
import ge.mimino.travel.request.AddCaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ucha
 */
@Service
public class CaseService {

    @Autowired
    private CaseDAO caseDAO;


    public List<CaseDTO> getCases(int start, int limit, AddCaseRequest srchRequest) {
        return CaseDTO.parseToList(caseDAO.getAll(Case.class));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Case save(AddCaseRequest request) throws Exception {

        Case obj = new Case();
        obj.setContactEmail(request.getContactEmail());
        obj.setCombined(request.getCombined());
        obj.setTourStart(request.getTourStart());
        obj.setTourEnd(request.getTourEnd());
        obj.setDaysCount(request.getDaysCount());
        obj.setNightsCount(request.getNightsCount());
        obj.setTouristsCount(request.getTouristsCount());
        obj.setTouristsCountNote(request.getTouristsCountNote());
        obj.setArrivalTime(request.getArrivalTime());
        obj.setLeaveTime(request.getLeaveTime());
        obj.setTourType(request.getTourType());
        obj.setGuideDriver(request.getGuideDriver());
        obj.setHotelCategory(request.getHotelCategory());
        obj.setEntranceFees(request.getEntranceFees());
        obj.setComment(request.getComment());
        obj.setBudget(request.getBudget());

        obj.setCurrency((Currency) caseDAO.find(Currency.class, request.getCurrencyId()));
        obj.setArrivalCity((City) caseDAO.find(City.class, request.getArrivalCityId()));
        obj.setLeaveCity((City) caseDAO.find(City.class, request.getLeaveCityId()));
        obj.setMealCategory((MealCategory) caseDAO.find(MealCategory.class, request.getMealCategoryId()));
        obj.setPackageCategory((PackageCategory) caseDAO.find(PackageCategory.class, request.getPackageCategoryId()));

        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Case) caseDAO.update(obj);
        } else {
            obj = (Case) caseDAO.create(obj);
        }
        return obj;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        Case obj = (Case) caseDAO.find(Case.class, id);
        if (obj != null) {
            caseDAO.delete(obj);
        }
    }

    public List<CaseCountryDTO> getCaseCountries(int caseId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("caseId", caseId));
        return CaseCountryDTO.parseToList(caseDAO.getAllByParamValue(CaseCountry.class, paramValues, null));
    }

    public List<CaseDetailsDTO> getCaseDetails(int caseId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("caseId", caseId));
        return CaseDetailsDTO.parseToList(caseDAO.getAllByParamValue(CaseDetails.class, paramValues, null));
    }

    public List<DetailsDTO> getDetails() {
        return DetailsDTO.parseToList(caseDAO.getAll(Details.class));
    }
}