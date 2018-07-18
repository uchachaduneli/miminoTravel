package ge.mimino.travel.controller;

import ge.mimino.travel.dto.HotelDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.FileService;
import ge.mimino.travel.service.HotelService;
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
@RequestMapping("/hotels")
@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private FileService fileService;

    @RequestMapping("/get-hotels")
    @ResponseBody
    private Response getHotel(@RequestParam("start") int start, @RequestParam("limit") int limit,
                              @RequestBody HotelDTO request) throws Exception {
        return Response.withSuccess(hotelService.getHotels(start, limit, request));
    }

    @RequestMapping({"/save"})
    @ResponseBody
    public Response saveUser(@RequestBody HotelDTO request) throws Exception {
        return Response.withSuccess(HotelDTO.parse(hotelService.save(request)));
    }

    @RequestMapping({"/save-images"})
    @ResponseBody
    public Response saveUser(@RequestBody List<String> images, @RequestParam Integer id) throws Exception {
        if (images == null || id == null) {
            return Response.withError("Empty List of Images");
        }
        hotelService.saveImages(images, id);
        return Response.ok();
    }

    @RequestMapping({"/delete"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        hotelService.delete(id);
        return Response.withSuccess(true);
    }

    @RequestMapping("/get-images")
    @ResponseBody
    private Response getHotelImages(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(hotelService.getHotelImages(id));
    }

    @RequestMapping("/add-images")
    @ResponseBody
    private Response addImage(@RequestParam("file") MultipartFile file, @RequestParam String id) throws IOException {
        return Response.withSuccess(fileService.addFile(file, "uploads", id));
    }
}
