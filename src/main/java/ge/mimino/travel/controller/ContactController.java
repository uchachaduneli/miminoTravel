package ge.mimino.travel.controller;

import ge.mimino.travel.dto.ContactDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.request.AddContactRequest;
import ge.mimino.travel.service.ContactService;
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
@RequestMapping("/contact")
@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/get-contacts")
    @ResponseBody
    private Response getContacts(@RequestBody AddContactRequest request, HttpServletRequest servletRequest) throws Exception {
        request.setUserId((Integer) servletRequest.getSession().getAttribute("userId"));
        return Response.withSuccess(contactService.getContacts());
    }

    @RequestMapping("/get-types")
    @ResponseBody
    private Response getTypes() throws Exception {
        return Response.withSuccess(contactService.getTypes());
    }

    @RequestMapping("/get-contact-types")
    @ResponseBody
    private Response getContactTypes(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(contactService.getContactTypes(id));
    }

    @RequestMapping("/get-categories")
    @ResponseBody
    private Response getCategories() throws Exception {
        return Response.withSuccess(contactService.getCategories());
    }

    @RequestMapping("/get-contact-categories")
    @ResponseBody
    private Response getContactCategories(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(contactService.getContactCategories(id));
    }

    @RequestMapping("/get-statuses")
    @ResponseBody
    private Response getStatuses() throws Exception {
        return Response.withSuccess(contactService.getContactStatuses());
    }

    @RequestMapping("/get-status-history")
    @ResponseBody
    private Response getStatusHistory(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(contactService.getContactStatusHistory(id));
    }

    @RequestMapping("/get-ranks")
    @ResponseBody
    private Response getRanks() throws Exception {
        return Response.withSuccess(contactService.getContactRanks());
    }

    @RequestMapping({"/save"})
    @ResponseBody
    public Response saveUser(@RequestBody AddContactRequest request, HttpServletRequest servletRequest) throws Exception {
        request.setUserId((Integer) servletRequest.getSession().getAttribute("userId"));
        return Response.withSuccess(ContactDTO.parse(contactService.save(request)));
    }

    @RequestMapping({"/delete-contact"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        contactService.delete(id);
        return Response.withSuccess(true);
    }

}
