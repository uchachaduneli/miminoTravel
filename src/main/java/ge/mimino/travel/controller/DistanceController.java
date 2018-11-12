package ge.mimino.travel.controller;

import ge.mimino.travel.dto.DistancesDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.DistanceService;
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
@RequestMapping("/distances")
@Controller
public class DistanceController {

  @Autowired
  private DistanceService distanceService;

  @RequestMapping("/get-distances")
  @ResponseBody
  private Response getDistances(@RequestParam("start") int start, @RequestParam("limit") int limit,
                                @RequestBody DistancesDTO request) throws Exception {
    return Response.withSuccess(distanceService.getDistances(start, limit, request));
  }

  @RequestMapping({"/save"})
  @ResponseBody
  public Response saveUser(@RequestBody DistancesDTO request) throws Exception {
    return Response.withSuccess(DistancesDTO.parse(distanceService.save(request)));
  }


  @RequestMapping({"/get-distances-by-place"})
  @ResponseBody
  public Response getDistancesByPlace(@RequestBody List<Integer> request) throws Exception {
    return Response.withSuccess(distanceService.getDistancesByPlace(request));
  }

  @RequestMapping({"/delete-distance"})
  @ResponseBody
  public Response delete(@RequestParam int id) {
    distanceService.delete(id);
    return Response.withSuccess(true);
  }
}
