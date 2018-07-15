package ge.mimino.travel.controller;

import ge.mimino.travel.dto.GeoObjectDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.GeoObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ucha
 */
@RequestMapping("/geoObject")
@Controller
public class GeoObjectController {

    @Autowired
    private GeoObjectService geoObjectService;

    @RequestMapping("/get-geoObjects")
    @ResponseBody
    private Response getGeoObjects(@RequestParam("start") int start, @RequestParam("limit") int limit,
                                   @RequestBody GeoObjectDTO request) throws Exception {
        return Response.withSuccess(geoObjectService.getGeoObjects(start, limit, request));
    }

    @RequestMapping({"/save"})
    @ResponseBody
    public Response saveUser(@RequestBody GeoObjectDTO request) throws Exception {
        return Response.withSuccess(GeoObjectDTO.parse(geoObjectService.save(request)));
    }

    @RequestMapping({"/delete"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        geoObjectService.delete(id);
        return Response.withSuccess(true);
    }

    @RequestMapping("/get-types")
    @ResponseBody
    private Response getGeoObjectTypes() throws Exception {
        return Response.withSuccess(geoObjectService.getGeoObjectTypes());
    }

    @RequestMapping("/get-images")
    @ResponseBody
    private Response getGeoObjectImages() throws Exception {
        return Response.withSuccess(geoObjectService.getGeoObjectImages());
    }
}
