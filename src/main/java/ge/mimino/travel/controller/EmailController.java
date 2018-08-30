package ge.mimino.travel.controller;

import ge.mimino.travel.dto.EmailDTO;
import ge.mimino.travel.dto.UsersDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.model.Users;
import ge.mimino.travel.request.MailRequest;
import ge.mimino.travel.service.FileService;
import ge.mimino.travel.service.MailService;
import ge.mimino.travel.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author ucha
 */
@RequestMapping("/emails")
@Controller
public class EmailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UsersService userService;

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

    @RequestMapping("/add-attachment")
    @ResponseBody
    private Response addAttachments(@RequestParam("file") MultipartFile file) throws IOException {
        return Response.withSuccess(fileService.addFile(file, "attachments", ""));
    }

    @RequestMapping("/get-attachment")
    @ResponseBody
    private Response getAttachments(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(mailService.getEmailAttachments(id));
    }

    @RequestMapping({"/send"})
    @ResponseBody
    public Response saveUser(@RequestBody EmailDTO request, HttpServletRequest servletRequest) throws Exception {

        request.setUserId((Integer) servletRequest.getSession().getAttribute("userId"));
        Users user = userService.getUserById(request.getUserId());

        List<String> cc = (request.getCc() != null && !request.getCc().isEmpty()) ? new ArrayList<String>(Arrays.asList(request.getCc().split(";"))) : new ArrayList<>();
        List<String> filePaths = new ArrayList<>();
        mailService.sendEmail(user.getEmail(), user.getEmailPassword(), request.getTo(), cc, request.getReply(),
                new ArrayList<String>(Arrays.asList(request.getAttachments().split(";"))),
                request.getSubject(), request.getContent(), request.getAttachments(), user);
        return Response.ok();
    }

}
