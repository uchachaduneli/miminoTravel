package ge.mimino.travel.service;


import ge.mimino.travel.dao.MiscDAO;
import ge.mimino.travel.dto.*;
import ge.mimino.travel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class MiscService {

    @Autowired
    private MiscDAO miscDAO;


    public List<CountryDTO> getCountries() {
        return CountryDTO.parseToList(miscDAO.getAll(Country.class));
    }

    public List<CityDTO> getCities() {
        return CityDTO.parseToList(miscDAO.getAll(City.class));
    }

    public List<MealCategoryDTO> getMealCategories() {
        return MealCategoryDTO.parseToList(miscDAO.getAll(MealCategory.class));
    }

    public List<PackageCategoryDTO> getPackageCategories() {
        return PackageCategoryDTO.parseToList(miscDAO.getAll(PackageCategory.class));
    }

    public List<CurrencyDTO> getCurrencies() {
        return CurrencyDTO.parseToList(miscDAO.getAll(Currency.class));
    }

}
