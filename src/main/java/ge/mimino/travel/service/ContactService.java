package ge.mimino.travel.service;


import ge.mimino.travel.dao.ContactDAO;
import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dto.ContactDTO;
import ge.mimino.travel.dto.ContactRankDTO;
import ge.mimino.travel.dto.ContactTypeDTO;
import ge.mimino.travel.dto.ContactTypesDTO;
import ge.mimino.travel.model.*;
import ge.mimino.travel.request.AddContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ucha
 */
@Service
public class ContactService {

    @Autowired
    private ContactDAO contactDAO;


    public List<ContactDTO> getContacts() {
        return ContactDTO.parseToList(contactDAO.getAll(Contact.class));
    }

    public List<ContactTypeDTO> getTypes() {
        return ContactTypeDTO.parseToList(contactDAO.getAll(ContactType.class));
    }

    public List<ContactRankDTO> getContactRanks() {
        return ContactRankDTO.parseToList(contactDAO.getAll(ContactRank.class));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Contact save(AddContactRequest request) throws Exception {

        Contact obj = new Contact();

        obj.setActivity(request.getActivity());
        obj.setCity(request.getCity());
        obj.setContactPerson(request.getContactPerson());
        obj.setCountry((Country) contactDAO.find(Country.class, request.getCountryId() == null ?
                request.getCountry().getId() : request.getCountryId()));
        obj.setActivity(request.getActivity());
        obj.setInfo(request.getInfo());
        obj.setLastActivity(request.getLastActivity());
        obj.setNextActivity(request.getNextActivity());
        obj.setName(request.getName());
        obj.setSource(request.getSource());
        obj.setWebsite(request.getWebsite());
        obj.setPhone(request.getPhone());
        obj.setCountry((Country) contactDAO.find(Country.class, request.getCountryId() == null ?
                request.getCountry().getId() : request.getCountryId()));
        obj.setRank((ContactRank) contactDAO.find(ContactRank.class, request.getRankId() == null ?
                request.getRank().getId() : request.getRankId()));

        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Contact) contactDAO.update(obj);
        } else {
            obj = (Contact) contactDAO.create(obj);
        }
        return obj;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        Contact obj = (Contact) contactDAO.find(Contact.class, id);
        if (obj != null) {
            contactDAO.delete(obj);
        }
    }

    public List<ContactTypesDTO> getContactTypes(int contactId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("contactId", contactId));
        return ContactTypesDTO.parseToList(contactDAO.getAllByParamValue(ContactTypes.class, paramValues, null));
    }
}