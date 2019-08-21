package ge.mimino.travel.controller;

import ge.mimino.travel.misc.Response;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Response handleException(HttpServletRequest request, Exception ex) {
        logger.error("{{{ Exception Occured, URL=" + request.getRequestURL() + "\t" + ex.getMessage() + "  }}}", ex);
        return Response.withError("Error Occured: " + ex.getLocalizedMessage());
    }

}
