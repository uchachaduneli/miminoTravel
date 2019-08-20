package ge.mimino.travel.service;


import ge.mimino.travel.dao.MiscDAO;
import ge.mimino.travel.dto.*;
import ge.mimino.travel.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class MiscService {
    Logger logger = Logger.getLogger(MiscService.class);

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

    public List<LanguageDTO> getLanguages() {
        return LanguageDTO.parseToList(miscDAO.getAll(Language.class));
    }

    public List<RegionDTO> getRegions() {
        return RegionDTO.parseToList(miscDAO.getAll(Region.class));
    }

}
