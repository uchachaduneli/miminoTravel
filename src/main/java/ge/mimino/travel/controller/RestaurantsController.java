package ge.mimino.travel.controller;

import ge.mimino.travel.dto.RestaurantDTO;
import ge.mimino.travel.dto.RestaurantMealCatsDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.FileService;
import ge.mimino.travel.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


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

    @RequestMapping("/get-restaurant-meal-cats")
    @ResponseBody
    private Response getRestaurantMealCats(@RequestParam("id") int restaurantId, @RequestParam("group") String group) throws Exception {
        return Response.withSuccess(restaurantService.getRestaurantMealCats(restaurantId, group));
    }

    @RequestMapping({"/save"})
    @ResponseBody
    public Response saveUser(@RequestBody RestaurantDTO request) throws Exception {
        return Response.withSuccess(RestaurantDTO.parse(restaurantService.save(request)));
    }

    @RequestMapping({"/save-meal-categories"})
    @ResponseBody
    public Response saveMealCats(@RequestParam("restaurantId") Integer restaurantId, @RequestBody List<RestaurantMealCatsDTO> request) throws Exception {
        restaurantService.saveMealCats(request, restaurantId);
        return Response.ok();
    }

    @RequestMapping({"/delete"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        restaurantService.delete(id);
        return Response.withSuccess(true);
    }

    @RequestMapping("/get-restaurant-packages")
    @ResponseBody
    private Response getRestaurantPackages(@RequestParam Integer id) throws Exception {
        return Response.withSuccess(restaurantService.getRestaurantPackages(id));
    }

    @RequestMapping("/get-restaurants-by-place")
    @ResponseBody
    private Response getRestaurantsByPlaces(@RequestBody List<Integer> ids) throws Exception {
        return Response.withSuccess(restaurantService.getRestaurantsByPlaces(ids));
    }
}
