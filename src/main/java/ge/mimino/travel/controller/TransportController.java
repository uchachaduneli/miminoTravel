package ge.mimino.travel.controller;

import ge.mimino.travel.dto.FuelDTO;
import ge.mimino.travel.dto.TransportDTO;
import ge.mimino.travel.misc.Response;
import ge.mimino.travel.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ucha
 */
@RequestMapping("/transports")
@Controller
public class TransportController {

    @Autowired
    private TransportService transportService;

    @RequestMapping("/get-transports")
    @ResponseBody
    private Response getTransports(@RequestParam("start") int start, @RequestParam("limit") int limit,
                                   @RequestBody TransportDTO request) throws Exception {
        return Response.withSuccess(transportService.getTransports(start, limit, request));
    }

    @RequestMapping({"/save"})
    @ResponseBody
    public Response saveUser(@RequestBody TransportDTO request) throws Exception {
        return Response.withSuccess(TransportDTO.parse(transportService.save(request)));
    }

    @RequestMapping({"/delete"})
    @ResponseBody
    public Response delete(@RequestParam int id) {
        transportService.delete(id);
        return Response.withSuccess(true);
    }

    @RequestMapping("/get-fuels")
    @ResponseBody
    private Response getFuels() throws Exception {
        return Response.withSuccess(transportService.getFuels());
    }

    @RequestMapping({"/save-fuel"})
    @ResponseBody
    public Response saveUser(@RequestBody FuelDTO request) throws Exception {
        return Response.withSuccess(FuelDTO.parse(transportService.saveFuel(request)));
    }
}
