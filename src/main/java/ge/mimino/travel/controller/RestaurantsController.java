package ge.mimino.travel.controller;

import ge.mimino.travel.dto.RestaurantDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.FileService;
import ge.mimino.travel.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ucha
 */
@RequestMapping("/restaurants")
@Controller
public class RestaurantsController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FileService fileService;

    @RequestMapping("/get-restaurants")
    @ResponseBody
    private Response getRestaurants(@RequestParam("start") int start, @RequestParam("limit") int limit,
                                    @RequestBody RestaurantDTO request) throws Exception {
        return Response.withSuccess(restaurantService.getRestaurants(start, limit, request));
    }

    @RequestMapping({"/save"})
    @ResponseBody
    public Response saveUser(@RequestBody RestaurantDTO request) throws Exception {
        return Response.withSuccess(RestaurantDTO.parse(restaurantService.save(request)));
    }

    @RequestMapping({"/delete"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        restaurantService.delete(id);
        return Response.withSuccess(true);
    }
}
