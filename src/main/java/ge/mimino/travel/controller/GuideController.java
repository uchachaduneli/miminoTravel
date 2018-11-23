package ge.mimino.travel.controller;

import ge.mimino.travel.misc.Response;
import ge.mimino.travel.model.Guide;
import ge.mimino.travel.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ucha
 */
@RequestMapping("/guides")
@Controller
public class GuideController {

  @Autowired
  private GuideService guideService;

  @RequestMapping("/get-guides")
  @ResponseBody
  private Response getGuides(@RequestParam("start") int start, @RequestParam("limit") int limit,
                             @RequestBody Guide request) throws Exception {
    return Response.withSuccess(guideService.getGuides(start, limit, request));
  }

  @RequestMapping({"/save"})
  @ResponseBody
  public Response save(@RequestBody Guide request) throws Exception {
    return Response.withSuccess(guideService.save(request));
  }

  @RequestMapping({"/delete"})
  @ResponseBody
  public Response delete(@RequestParam int id) {
    guideService.delete(id);
    return Response.withSuccess(true);
  }

}
