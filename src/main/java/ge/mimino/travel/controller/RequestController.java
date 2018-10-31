package ge.mimino.travel.controller;

import ge.mimino.travel.dto.RequestDTO;
import ge.mimino.travel.dto.RequestMessageDTO;
import ge.mimino.travel.dto.UsersDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.request.AddRequest;
import ge.mimino.travel.request.ProductRequest;
import ge.mimino.travel.service.FileService;
import ge.mimino.travel.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

  @RequestMapping({"/save"})
  @ResponseBody
  public Response saveUser(@RequestBody AddRequest request, HttpServletRequest servletRequest) throws Exception {
    request.setUserId((Integer) servletRequest.getSession().getAttribute("userId"));
    return Response.withSuccess(RequestDTO.parse(requestService.save(request)));
  }

  @RequestMapping({"/save-product"})
  @ResponseBody
  public Response saveUser(@RequestBody ProductRequest request) throws Exception {
    requestService.saveProduct(request);
    return Response.ok();
  }

  @RequestMapping({"/save-message"})
  @ResponseBody
  public Response saveUser(@RequestBody RequestMessageDTO request, HttpServletRequest servletRequest) throws Exception {
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

  @RequestMapping("/save-images")
  @ResponseBody
  private Response getSaveImages(@RequestParam String imgName) throws Exception {
    requestService.saveImages(imgName);
    return Response.ok();
  }

  @RequestMapping("/get-product-images")
  @ResponseBody
  private Response getProductImages() throws Exception {
    return Response.withSuccess(requestService.getProductImages());
  }

  @RequestMapping("/add-images")
  @ResponseBody
  private Response addImage(@RequestParam("file") MultipartFile file, @RequestParam String id) throws IOException {
    return Response.withSuccess(fileService.addFile(file, "uploads", id));
  }

}
