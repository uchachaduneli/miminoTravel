package ge.mimino.travel.controller;

import ge.mimino.travel.dto.PlaceDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ucha
 */
@RequestMapping("/place")
@Controller
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping("/get-places")
    @ResponseBody
    private Response getPlaces(@RequestParam("start") int start, @RequestParam("limit") int limit,
                               @RequestBody PlaceDTO request) throws Exception {
        return Response.withSuccess(placeService.getPlaces(start, limit, request));
    }

    @RequestMapping({"/save"})
    @ResponseBody
    public Response saveUser(@RequestBody PlaceDTO request) throws Exception {
        return Response.withSuccess(PlaceDTO.parse(placeService.save(request)));
    }

    @RequestMapping({"/delete-place"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        placeService.delete(id);
        return Response.withSuccess(true);
    }

}
