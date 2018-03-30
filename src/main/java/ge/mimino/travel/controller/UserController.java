package ge.mimino.travel.controller;

import ge.mimino.travel.dto.UsersDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.request.AddUserRequest;
import ge.mimino.travel.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @author ucha
 */
@RequestMapping("/users")
@Controller
public class UserController {

    @Autowired
    private UsersService userService;

    @RequestMapping("/get-users")
    @ResponseBody
    private Response getUsers() throws Exception {
        return Response.withSuccess(userService.getUsers());
    }

    @RequestMapping("/get-user-types")
    @ResponseBody
    private Response getUserTypes() throws Exception {
        return Response.withSuccess(userService.getUserTypes());
    }

    @RequestMapping({"/save-user"})
    @ResponseBody
    public Response saveUser(@RequestBody AddUserRequest request) throws Exception {
        return Response.withSuccess(UsersDTO.parse(userService.saveUser(request)));
    }

    @RequestMapping({"/change-password"})
    @ResponseBody
    public Response saveUser(HttpServletRequest servletRequest, @RequestParam String pass, @RequestParam String newpass) throws Exception {
        Integer userId = (Integer) servletRequest.getSession().getAttribute("userId");
        if (userId != null) {
            return Response.withSuccess(userService.changePassword(userId, pass, newpass));
        } else {
            return Response.withError("პაროლის შესაცვლელად გაიარეთ ავტორიზაცია");
        }
    }

    @RequestMapping({"/delete-user"})
    @ResponseBody
    public Response deleteUser(@RequestParam int id) {
        userService.delete(id);
        return Response.withSuccess(true);
    }

}
