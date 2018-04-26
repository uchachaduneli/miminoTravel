package ge.mimino.travel.service;


import ge.mimino.travel.dao.ContactDAO;
import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dto.*;
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


    public List<ContactDTO> getContacts(int start, int limit, AddContactRequest srchRequest) {
        return ContactDTO.parseToList(contactDAO.getContacts(start, limit, srchRequest));
    }

    public List<ContactTypeDTO> getTypes() {
        return ContactTypeDTO.parseToList(contactDAO.getAll(ContactType.class));
    }

    public List<ContactTypesDTO> getContactTypes(int contactId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("contactId", contactId));
        return ContactTypesDTO.parseToList(contactDAO.getAllByParamValue(ContactTypes.class, paramValues, null));
    }

    public List<ContactCategoryDTO> getCategories() {
        return ContactCategoryDTO.parseToList(contactDAO.getAll(ContactCategory.class));
    }

    public List<ContactCategoriesDTO> getContactCategories(int contactId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("contactId", contactId));
        return ContactCategoriesDTO.parseToList(contactDAO.getAllByParamValue(ContactCategories.class, paramValues, null));
    }

    public List<ContactCategoryDTO> getContactStatuses() {
        return ContactStatusDTO.parseToList(contactDAO.getAll(ContactStatus.class));
    }

    public List<ContactStatusHistoryDTO> getContactStatusHistory(int contactId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("contactId", contactId));
        return ContactStatusHistoryDTO.parseToList(contactDAO.getAllByParamValue(ContactStatusHistory.class, paramValues, null));
    }

    public List<ContactRankDTO> getContactRanks() {
        return ContactRankDTO.parseToList(contactDAO.getAll(ContactRank.class));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Contact save(AddContactRequest request) throws Exception {

        Contact obj = request.getId() != null ? ((Contact) contactDAO.find(Contact.class, request.getId())) : new Contact();

        obj.setActivity(request.getActivity());
        obj.setCity(request.getCity());
        obj.setContactPerson(request.getContactPerson());
        obj.setCountry((Country) contactDAO.find(Country.class, request.getCountryId()));
        obj.setUser((Users) contactDAO.find(Users.class, request.getUserId()));
        obj.setActivity(request.getActivity());
        obj.setInfo(request.getInfo());
        obj.setLastActivity(request.getLastActivity());
        obj.setNextActivity(request.getNextActivity());
        obj.setName(request.getName());
        obj.setSource(request.getSource());
        obj.setWebsite(request.getWebsite());
        obj.setPhone(request.getPhone());
        obj.setEmail(request.getEmail());
        obj.setRank((ContactRank) contactDAO.find(ContactRank.class, request.getRankId()));

        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Contact) contactDAO.update(obj);
        } else {
            obj = (Contact) contactDAO.create(obj);
        }
        contactDAO.removeContactCategories(obj.getId());
        if (request.getCategories() != null && !request.getCategories().isEmpty()) {
            for (Integer catId : request.getCategories()) {
                contactDAO.create(new ContactCategories(obj.getId(), ((ContactCategory) contactDAO.find(ContactCategory.class, catId))));
            }
        }
        contactDAO.removeContactTypes(obj.getId());
        if (request.getTypes() != null && !request.getTypes().isEmpty()) {
            for (Integer typeId : request.getTypes()) {
                contactDAO.create(new ContactTypes(obj.getId(), ((ContactType) contactDAO.find(ContactType.class, typeId))));
            }
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
}