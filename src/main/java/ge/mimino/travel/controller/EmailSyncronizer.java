package ge.mimino.travel.controller;

import ge.mimino.travel.dto.UsersDTO;
import ge.mimino.travel.misc.Response;
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
    private UsersService userService;

    @Autowired
    private MailService mailService;

    @RequestMapping("/load")
    @ResponseBody
    private Response syncronyze() throws Exception {
//        String senderPassword = new String("1qaz@WSX1qaz");
//        String senderUserName = new String("miminotest@yandex.ru");
//
//        List<String> addresats = new ArrayList<>();
//        addresats.add("uchachaduneli@gmail.com");
//
//        List<String> attachments = new ArrayList<>();
////        attachments.add("C:\\Users\\ME\\IdeaProjects\\miminoTravel\\src\\main\\resources\\0012.pdf");
//
//        mailService.sendEmail(senderUserName, senderPassword,
//                addresats, null, attachments, "TEST MAIL SUBJECT", "ეს არის სატესტო მეილის ტექსტი " + new Date().getTime());
//
//        for(UsersDTO user: userService.getUsers()){
//
//        }


        List<UsersDTO> users = userService.getUsers();
        for (UsersDTO user : users) {
            if (user.getEmail() != null && user.getEmailPassword() != null && user.getDeleted() != UsersDTO.DELETED) {
                try {
                    mailService.downloadEmailAttachments(user.getEmail(), user.getEmailPassword());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return Response.ok();
    }
}
