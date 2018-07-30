package ge.mimino.travel.controller;

import ge.mimino.travel.dto.RequestDTO;
import ge.mimino.travel.dto.UsersDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.request.AddRequest;
import ge.mimino.travel.service.RequestService;
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
@RequestMapping("/requests")
@Controller
public class RequestController {

    @Autowired
    private RequestService requestService;

    @RequestMapping("/get-requests")
    @ResponseBody
    private Response getRequests(@RequestParam("start") int start, @RequestParam("limit") int limit,
                                 @RequestBody AddRequest request, HttpServletRequest servletRequest) throws Exception {
        if (((Integer) servletRequest.getSession().getAttribute("typeId")) == UsersDTO.COMUNICATION_MANAGER) {
            request.setUserId((Integer) servletRequest.getSession().getAttribute("userId"));
        }
        return Response.withSuccess(requestService.getRequests(start, limit, request));
    }

    @RequestMapping("/get-requests-countries")
    @ResponseBody
    private Response getRequestsCountries(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(requestService.getRequestCountries(id));
    }

    @RequestMapping("/get-request-by-key")
    @ResponseBody
    private Response getRequestByKey(@RequestParam String id) {
        try {
            return Response.withSuccess(RequestDTO.parse(requestService.getRequestByKey(id)));
        } catch (IndexOutOfBoundsException ex) {
            return Response.withError("Incorrect Key");
        }
    }

    @RequestMapping("/get-requests-details")
    @ResponseBody
    private Response getRequestsDetails(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(requestService.getRequestDetails(id));
    }

    @RequestMapping("/get-details")
    @ResponseBody
    private Response getDetails() throws Exception {
        return Response.withSuccess(requestService.getDetails());
    }

    @RequestMapping({"/save"})
    @ResponseBody
    public Response saveUser(@RequestBody AddRequest request, HttpServletRequest servletRequest) throws Exception {
        request.setUserId((Integer) servletRequest.getSession().getAttribute("userId"));
        return Response.withSuccess(RequestDTO.parse(requestService.save(request)));
    }

    @RequestMapping({"/delete"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        requestService.delete(id);
        return Response.withSuccess(true);
    }

}
