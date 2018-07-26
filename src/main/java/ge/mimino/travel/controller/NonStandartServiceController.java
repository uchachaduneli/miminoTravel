package ge.mimino.travel.controller;

import ge.mimino.travel.dto.NonstandartServiceDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.NonStandartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ucha
 */
@RequestMapping("/nonstandarts")
@Controller
public class NonStandartServiceController {

    @Autowired
    private NonStandartService nonstandartService;

    @RequestMapping("/get-nonstandart-services")
    @ResponseBody
    private Response getNonstandarts(@RequestParam("start") int start, @RequestParam("limit") int limit,
                                     @RequestBody NonstandartServiceDTO request) throws Exception {
        return Response.withSuccess(nonstandartService.getNonStandartServices(start, limit, request));
    }

    @RequestMapping({"/save"})
    @ResponseBody
    public Response saveUser(@RequestBody NonstandartServiceDTO request) throws Exception {
        return Response.withSuccess(NonstandartServiceDTO.parse(nonstandartService.save(request)));
    }

    @RequestMapping({"/delete"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        nonstandartService.delete(id);
        return Response.withSuccess(true);
    }
}
