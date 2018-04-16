package ge.mimino.travel.service;


import ge.mimino.travel.dao.CaseDAO;
import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dto.CaseCountryDTO;
import ge.mimino.travel.dto.CaseDTO;
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


    public List<CaseDTO> getCases() {
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
        obj.setCurrency(request.getCurrency());
        obj.setComment(request.getComment());
        obj.setBudget(request.getBudget());

        obj.setArrivalCity((City) caseDAO.find(City.class, request.getArrivalCityId() == null ?
                request.getArrivalCity().getId() : request.getArrivalCityId()));
        obj.setLeaveCity((City) caseDAO.find(City.class, request.getLeaveCityId() == null ?
                request.getLeaveCity().getId() : request.getLeaveCityId()));
        obj.setMealCategory((MealCategory) caseDAO.find(MealCategory.class, request.getMealCategoryId() == null ?
                request.getMealCategory().getId() : request.getMealCategoryId()));
        obj.setPackageCategory((PackageCategory) caseDAO.find(PackageCategory.class, request.getPackageCategoryId() == null ?
                request.getPackageCategory().getId() : request.getPackageCategoryId()));

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
}