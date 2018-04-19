package ge.mimino.travel.controller;

import ge.mimino.travel.dto.CaseDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.request.AddCaseRequest;
import ge.mimino.travel.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ucha
 */
@RequestMapping("/cases")
@Controller
public class CaseController {

    @Autowired
    private CaseService caseService;

    @RequestMapping("/get-cases")
    @ResponseBody
    private Response getContacts() throws Exception {
        return Response.withSuccess(caseService.getCases());
    }

    @RequestMapping("/get-case-countries")
    @ResponseBody
    private Response getCaseCountries(@RequestParam Integer caseId) throws Exception {
        return Response.withSuccess(caseService.getCaseCountries(caseId));
    }

    @RequestMapping({"/save-case"})
    @ResponseBody
    public Response saveUser(@RequestBody AddCaseRequest request) throws Exception {
        return Response.withSuccess(CaseDTO.parse(caseService.save(request)));
    }

    @RequestMapping({"/delete-contact"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        caseService.delete(id);
        return Response.withSuccess(true);
    }

}
