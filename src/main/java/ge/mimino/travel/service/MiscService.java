package ge.mimino.travel.service;


import ge.mimino.travel.dao.MiscDAO;
import ge.mimino.travel.dto.CountryDTO;
import ge.mimino.travel.model.Country;
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

}
