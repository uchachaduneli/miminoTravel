package ge.mimino.travel.controller;

import ge.mimino.travel.dto.UsersDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.model.Email;
import ge.mimino.travel.model.Users;
import ge.mimino.travel.request.MailRequest;
import ge.mimino.travel.service.MailService;
import ge.mimino.travel.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author ucha
 */
@RequestMapping("/syncronyzer")
@Controller
public class EmailSyncronizer {

    @Autowired
    private MailService mailService;

    @RequestMapping("/load")
    @ResponseBody
    private Response syncronyze() throws Exception {
        mailService.processUnreadEmails();
        return Response.ok();
    }
}
