package ge.mimino.travel.controller;

import ge.mimino.travel.dto.RequestDTO;
import ge.mimino.travel.dto.RequestMessageDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.request.AddRequest;
import ge.mimino.travel.request.ProductRequest;
import ge.mimino.travel.service.FileService;
import ge.mimino.travel.service.RequestService;
import ge.mimino.travel.service.UsersService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author ucha
 */
@RequestMapping("/requests")
@Controller
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/get-requests")
    @ResponseBody
    private Response getRequests(@RequestParam("start") int start, @RequestParam("limit") int limit,
                                 @RequestBody AddRequest request, HttpServletRequest servletRequest) throws Exception {
        request.setUserTypeId((Integer) servletRequest.getSession().getAttribute("typeId"));// Stage Id Igive
        request.setUserId((Integer) servletRequest.getSession().getAttribute("userId"));
        return Response.withSuccess(requestService.getRequests(start, limit, request));
    }

    @RequestMapping("/get-user-languages")
    @ResponseBody
    private Response getUserLanguages(HttpServletRequest servletRequest) throws Exception {
        return Response.withSuccess(usersService.getUserLanguagesDTO((Integer) servletRequest.getSession().getAttribute("userId")));
    }

    @RequestMapping("/get-requests-countries")
    @ResponseBody
    private Response getRequestsCountries(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(requestService.getRequestCountries(id));
    }

    @RequestMapping("/get-request-stages")
    @ResponseBody
    private Response getRequestStages(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(requestService.getRequestStages(id));
    }

    @RequestMapping("/change-stage")
    @ResponseBody
    private Response changeRequestStage(@RequestParam Integer requestId, @RequestParam Integer stageId, HttpServletRequest servletRequest) throws Exception {
        requestService.updateRequestStage(stageId, requestId, (Integer) servletRequest.getSession().getAttribute("userId"));
        return Response.ok();
    }

    @RequestMapping("/get-stages")
    @ResponseBody
    private Response getStages() throws Exception {
        return Response.withSuccess(requestService.getStages());
    }

    @RequestMapping("/update-transport-days")
    @ResponseBody
    private Response updateTransportDays(@RequestParam Integer reqId, @RequestParam String checkedDays) throws Exception {
        requestService.updateTransportDays(reqId, checkedDays);
        return Response.ok();
    }

    @RequestMapping("/get-tourist-counts")
    @ResponseBody
    private Response getTouristCounts(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(requestService.getTouristCounts(id));
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

    @RequestMapping("/get-product-details")
    @ResponseBody
    private Response getProductDetailsById(@RequestParam Integer requestId, @RequestParam Integer day) {
        return Response.withSuccess(requestService.getProductDetailsById(requestId, day));
    }

    @RequestMapping("/get-product-details-for-finaince")
    @ResponseBody
    private Response getProductDetailsForFinancialById(@RequestParam Integer requestId, @RequestParam Integer day) {
        return Response.withSuccess(requestService.getProductDetailsForFinancialById(requestId, day));
    }

    @RequestMapping("/get-requests-details")
    @ResponseBody
    private Response getRequestsDetails(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(requestService.getRequestDetails(id));
    }

    @RequestMapping("/get-requests-messages")
    @ResponseBody
    private Response getRequestMessages(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(requestService.getRequestMessages(id));
    }

    @RequestMapping("/get-details")
    @ResponseBody
    private Response getDetails() throws Exception {
        return Response.withSuccess(requestService.getDetails());
    }

    @RequestMapping("/get-tourists-count")
    @ResponseBody
    private Response getTouristsCount(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(requestService.getTouristsCount(id));
    }

    @RequestMapping({"/save"})
    @ResponseBody
    public Response save(@RequestBody AddRequest request, HttpServletRequest servletRequest) throws Exception {
        request.setUserId((Integer) servletRequest.getSession().getAttribute("userId"));
        return Response.withSuccess(RequestDTO.parse(requestService.save(request)));
    }

    @RequestMapping({"/save-product"})
    @ResponseBody
    public Response saveProduct(@RequestBody ProductRequest request) throws Exception {
        requestService.saveProduct(request);
        return Response.ok();
    }

    @RequestMapping({"/save-message"})
    @ResponseBody
    public Response saveMessage(@RequestBody RequestMessageDTO request, HttpServletRequest servletRequest) throws Exception {
        request.setUserId((Integer) servletRequest.getSession().getAttribute("userId"));
        requestService.saveMessage(request);
        return Response.ok();
    }

    @RequestMapping({"/delete"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        requestService.delete(id);
        return Response.withSuccess(true);
    }

    @RequestMapping("/add-images")
    @ResponseBody
    private Response addImage(@RequestParam("file") MultipartFile file, @RequestParam String id) throws IOException {
        return Response.withSuccess(fileService.addFile(file, "uploads", id));
    }

    @RequestMapping("/generate-word")
    @ResponseBody
    public Response downlodExcell(@RequestBody Integer requestId, HttpServletResponse response) throws IOException, InvalidFormatException {
        fileService.generateWord(requestId);
        return Response.ok();
    }

    @RequestMapping("/get-currencies")
    @ResponseBody
    public Response getCurrenciesByService() {
        return Response.withSuccess(requestService.getCurrency());
    }

}
