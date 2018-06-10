package ge.mimino.travel.controller;

import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.FileService;
import ge.mimino.travel.service.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author ucha
 */
@RequestMapping("/misc")
@Controller
public class MiscController {

    @Autowired
    private MiscService miscService;

    @Autowired
    private FileService fileService;

    @RequestMapping("/get-countries")
    @ResponseBody
    private Response geteCountries() throws Exception {
        return Response.withSuccess(miscService.getCountries());
    }

    @RequestMapping("/get-mealcategories")
    @ResponseBody
    private Response getMealCategories() throws Exception {
        return Response.withSuccess(miscService.getMealCategories());
    }

    @RequestMapping("/get-packagecategories")
    @ResponseBody
    private Response getPackageCategories() throws Exception {
        return Response.withSuccess(miscService.getPackageCategories());
    }

    @RequestMapping("/get-currencies")
    @ResponseBody
    private Response getCurrencies() throws Exception {
        return Response.withSuccess(miscService.getCurrencies());
    }

    @RequestMapping("/get-cities")
    @ResponseBody
    private Response getCities() throws Exception {
        return Response.withSuccess(miscService.getCities());
    }

    @RequestMapping("/get-languages")
    @ResponseBody
    private Response getLanguages() throws Exception {
        return Response.withSuccess(miscService.getLanguages());
    }

    @RequestMapping("/get-file")
    @ResponseBody
    private void getImage(HttpServletResponse response, @RequestParam String name) throws IOException {
        response.getOutputStream().write(fileService.readFile(name));
    }
}
