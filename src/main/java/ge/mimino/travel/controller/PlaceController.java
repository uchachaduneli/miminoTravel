package ge.mimino.travel.controller;

import ge.mimino.travel.dto.PlaceDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.FileService;
import ge.mimino.travel.service.PlaceService;
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
@RequestMapping("/places")
@Controller
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private FileService fileService;

    @RequestMapping("/get-places")
    @ResponseBody
    private Response getPlaces(@RequestParam("start") int start, @RequestParam("limit") int limit,
                               @RequestBody PlaceDTO request) throws Exception {
        return Response.withSuccess(placeService.getPlaces(start, limit, request));
    }

    @RequestMapping("/get-places-by-region")
    @ResponseBody
    private Response getPlaces(@RequestBody List<Integer> ids) throws Exception {
        return Response.withSuccess(placeService.getPlacesByRegion(ids));
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

    @RequestMapping("/get-images")
    @ResponseBody
    private Response getImages(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(placeService.getObjectImages(id));
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
        placeService.saveImages(images, id);
        return Response.ok();
    }

}
