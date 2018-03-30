package ge.mimino.travel.service;


import ge.mimino.travel.dao.UserDAO;
import ge.mimino.travel.dto.UsersDTO;
import ge.mimino.travel.dto.UsersTypeDTO;
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
    public Users saveUser(AddUserRequest userParam) throws Exception {

        Users user = new Users();

//        user.setFirstname(firstName);
//        user.setLastname(lastName);
//        user.setUsername(email);
//        user.setPassword(MD5Provider.doubleMd5(password));
//        user.setPhoneNumber(phoneNumber);
//        user.setViber(viber);
//        user.setSkype(skype);
//        user.setLoginType(loginType);
//        user.setToken(pushToken);
//        user = userDAO.create(user);

//        if (user.getUserId() != null) {
//            user = userDAO.update(user);
//        } else {
//            if (request.getTypeId() == UsersDTO.USER_RESTAURANT) {
//                Restaurants restaurants = new Restaurants();
//                restaurants.setIdentNumber(Integer.valueOf(user.getUsername()));
//                userDAO.create(restaurants);
//                user.setRestaurantId(restaurants.getRestaurantId());
//            }
//            user = userDAO.create(user);
//
//        }
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
}
