package ge.mimino.travel.controller;

import ge.mimino.travel.dto.UsersDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.request.MailRequest;
import ge.mimino.travel.service.MailService;
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
@RequestMapping("/emails")
@Controller
public class EmailController {

    @Autowired
    private MailService mailService;

    @RequestMapping("/get-emails")
    @ResponseBody
    private Response getEmails(@RequestParam("start") int start, @RequestParam("limit") int limit,
                               @RequestBody MailRequest request, HttpServletRequest servletRequest) throws Exception {
        if (((Integer) servletRequest.getSession().getAttribute("typeId")) == UsersDTO.COMUNICATION_MANAGER) {
            request.setUserId((Integer) servletRequest.getSession().getAttribute("userId"));
        }
        return Response.withSuccess(mailService.getEmails(start, limit, request));
    }

    @RequestMapping("/get-email-folders")
    @ResponseBody
    private Response getEmailFolders() throws Exception {
        return Response.withSuccess(mailService.getEmailFolders());
    }


}
