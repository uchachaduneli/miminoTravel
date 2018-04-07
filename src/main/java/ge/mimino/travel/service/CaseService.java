package ge.mimino.travel.service;


import ge.mimino.travel.dao.CaseDAO;
import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dto.CaseCountryDTO;
import ge.mimino.travel.dto.CaseDTO;
import ge.mimino.travel.model.Case;
import ge.mimino.travel.model.CaseCountry;
import ge.mimino.travel.request.AddUserRequest;
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
    public Case save(AddUserRequest request) throws Exception {

//        Users user = new Users();
//
//        user.setUserDesc(request.getUserDesc());
//        user.setUserName(request.getUserName());
//        user.setUserPassword(MD5Provider.doubleMd5(request.getUserPassword()));
//        user.setType((UserTypes) userDAO.find(UserTypes.class, request.getTypeId() == null ?
//                request.getType().getUserTypeId() : request.getTypeId()));
//        user.setLanguage((Language) userDAO.find(Language.class, request.getLanguageId() == null ?
//                request.getLanguage().getId() : request.getLanguageId()));
//        user.setDeleted(request.getDeleted());
//        user.setEmail(request.getEmail());
//        user.setEmailPassword(request.getEmailPassword());
//
//        if (request.getUserId() != null) {
//            user.setUserId(request.getUserId());
//            Users tmp = (Users) userDAO.find(Users.class, request.getUserId());
//            if (!request.getUserPassword().equals(tmp.getUserPassword())) {
//                user.setUserPassword(MD5Provider.doubleMd5(request.getUserPassword()));
//            }
//            user = (Users) userDAO.update(user);
//        } else {
//            user = (Users) userDAO.create(user);
//        }
        return null;//user;
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
