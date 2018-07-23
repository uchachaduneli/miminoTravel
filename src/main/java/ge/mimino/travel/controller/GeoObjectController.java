package ge.mimino.travel.controller;

import ge.mimino.travel.dto.GeoObjectDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.FileService;
import ge.mimino.travel.service.GeoObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


/**
 * @author ucha
 */
@RequestMapping("/objects")
@Controller
public class GeoObjectController {

    @Autowired
    private GeoObjectService geoObjectService;

    @Autowired
    private FileService fileService;

    @RequestMapping("/get-objects")
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
    private Response getGeoObjectImages(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(geoObjectService.getGeoObjectImages(id));
    }

    @RequestMapping("/add-images")
    @ResponseBody
    private Response addImage(@RequestParam("file") MultipartFile file, @RequestParam String id) throws IOException {
        return Response.withSuccess(fileService.addFile(file, "uploads", id));
    }

    @RequestMapping({"/save-images"})
    @ResponseBody
    public Response saveUser(@RequestBody List<String> images, @RequestParam Integer id) throws Exception {
        if (images == null || id == null) {
            return Response.withError("Empty List of Images");
        }
        geoObjectService.saveImages(images, id);
        return Response.ok();
    }
}
