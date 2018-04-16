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


/**
 * @author ucha
 */
@RequestMapping("/users")
@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/get-contacts")
    @ResponseBody
    private Response getContacts() throws Exception {
        return Response.withSuccess(contactService.getContacts());
    }

    @RequestMapping("/get-types")
    @ResponseBody
    private Response getTypes() throws Exception {
        return Response.withSuccess(contactService.getTypes());
    }

    @RequestMapping("/get-contact-types")
    @ResponseBody
    private Response getContactTypes(@RequestParam Integer contactId) throws Exception {
        return Response.withSuccess(contactService.getContactTypes(contactId));
    }

    @RequestMapping("/get-ranks")
    @ResponseBody
    private Response getRanks() throws Exception {
        return Response.withSuccess(contactService.getContactRanks());
    }

    @RequestMapping({"/save-contact"})
    @ResponseBody
    public Response saveUser(@RequestBody AddContactRequest request) throws Exception {
        return Response.withSuccess(ContactDTO.parse(contactService.save(request)));
    }

    @RequestMapping({"/delete-contact"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        contactService.delete(id);
        return Response.withSuccess(true);
    }

}
