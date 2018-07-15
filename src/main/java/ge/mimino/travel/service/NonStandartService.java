package ge.mimino.travel.service;


import ge.mimino.travel.dao.NonstandartDAO;
import ge.mimino.travel.dto.NonstandartServiceDTO;
import ge.mimino.travel.model.Language;
import ge.mimino.travel.model.NonstandartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class NonStandartService {

    @Autowired
    private NonstandartDAO nonStandartDAO;


    public List<NonstandartServiceDTO> getNonStandartServices(int start, int limit, NonstandartServiceDTO srchRequest) {
        return NonstandartServiceDTO.parseToList(nonStandartDAO.getNonStandartServices(start, limit, srchRequest));
    }

    @Transactional(rollbackFor = Throwable.class)
    public NonstandartService save(NonstandartServiceDTO request) throws Exception {

        NonstandartService obj = request.getId() != null ? ((NonstandartService) nonStandartDAO.find(NonstandartService.class, request.getId())) : new NonstandartService();
        obj.setLanguage((Language) nonStandartDAO.find(Language.class, request.getLanguageId()));
        obj.setDescription(request.getDescription());
        obj.setName(request.getName());
        obj.setPrice(request.getPrice());

        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (NonstandartService) nonStandartDAO.update(obj);
        } else {
            obj = (NonstandartService) nonStandartDAO.create(obj);
        }

        return obj;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        NonstandartService obj = (NonstandartService) nonStandartDAO.find(NonstandartService.class, id);
        if (obj != null) {
            nonStandartDAO.delete(obj);
        }
    }
}