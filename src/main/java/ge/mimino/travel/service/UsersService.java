package ge.mimino.travel.service;


import ge.mimino.travel.dao.UserDAO;
import ge.mimino.travel.dto.LanguageDTO;
import ge.mimino.travel.dto.UsersDTO;
import ge.mimino.travel.dto.UsersTypeDTO;
import ge.mimino.travel.model.Language;
import ge.mimino.travel.model.UserTypes;
import ge.mimino.travel.model.Users;
import ge.mimino.travel.request.AddUserRequest;
import ge.mimino.travel.utils.MD5Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author ucha
 */
@Service
public class UsersService {

    @Autowired
    private UserDAO userDAO;


    public List<UsersDTO> getUsers() {
        return UsersDTO.parseToList(userDAO.getAll(Users.class));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Users saveUser(AddUserRequest request) throws Exception {

        Users user = new Users();

        user.setUserDesc(request.getUserDesc());
        user.setUserName(request.getUserName());
        user.setUserPassword(MD5Provider.doubleMd5(request.getUserPassword()));
        user.setType((UserTypes) userDAO.find(UserTypes.class, request.getTypeId() == null ?
                request.getType().getUserTypeId() : request.getTypeId()));
        user.setLanguage((Language) userDAO.find(Language.class, request.getLanguageId() == null ?
                request.getLanguage().getId() : request.getLanguageId()));
        user.setDeleted(request.getDeleted());
        user.setEmail(request.getEmail());
        user.setEmailPassword(request.getEmailPassword());

        if (request.getUserId() != null) {
            user.setUserId(request.getUserId());
            Users tmp = (Users) userDAO.find(Users.class, request.getUserId());
            if (!request.getUserPassword().equals(tmp.getUserPassword())) {
                user.setUserPassword(MD5Provider.doubleMd5(request.getUserPassword()));
            }
            user = (Users) userDAO.update(user);
        } else {
            user = (Users) userDAO.create(user);
        }
        return user;
    }

    @Transactional(rollbackFor = Throwable.class)
    public UsersDTO changePassword(Integer userId, String pass, String newpass) throws IOException {

        Users user = userDAO.getEntityManager().find(Users.class, userId);
        // ვამოწმებთ შეცვლისას მითითებულ ძველ პაროლს, ცვლილებამდე თუ ემთხვევა არსებულს
        if (user.getUserPassword().equals(MD5Provider.doubleMd5(pass))) {

            if (user.getUserId() != null) {
                user.setUserPassword(MD5Provider.doubleMd5(newpass));
                user = (Users) userDAO.update(user);
            }

            return UsersDTO.parse(user);

        } else {
            return null;
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        Users user = (Users) userDAO.find(Users.class, id);
        if (user != null) {
            userDAO.delete(user);
        }
    }

    public UsersDTO login(String username, String password) {
        return UsersDTO.parse(userDAO.login(username, MD5Provider.doubleMd5(password)));
    }

    public List<UsersTypeDTO> getUserTypes() {
        return UsersTypeDTO.parseToList(userDAO.getAll(UserTypes.class));
    }

    public List<LanguageDTO> getLanguages() {
        return LanguageDTO.parseToList(userDAO.getAll(Language.class));
    }
}
