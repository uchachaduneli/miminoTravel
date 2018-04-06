package ge.mimino.travel.controller;

import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ucha
 */
@RequestMapping("/misc")
@Controller
public class MiscController {

    @Autowired
    private MiscService miscService;

    @RequestMapping("/get-countries")
    @ResponseBody
    private Response getCountries() throws Exception {
        return Response.withSuccess(miscService.getCountries());
    }
}
