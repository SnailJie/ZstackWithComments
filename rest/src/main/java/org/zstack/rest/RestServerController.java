package org.zstack.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zstack.header.rest.RESTConstant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.lang.reflect.InvocationTargetException;
/**
 * Created by xing5 on 2016/12/7.
 */
@Controller
public class RestServerController {
    @Autowired
    private RestServer server;

    /****
     * Handles all REST API requests and delegates processing to the RestServer.
     *
     * Sets a request attribute "info" containing a test map before forwarding the request and response to the RestServer for handling.
     *
     * @param request the incoming HTTP request
     * @param response the HTTP response to be sent
     * @throws IOException if an I/O error occurs during request handling
     * @throws IllegalAccessException if the underlying method is inaccessible
     * @throws NoSuchMethodException if a required method cannot be found
     * @throws InvocationTargetException if the underlying method throws an exception
     */
    @RequestMapping(
            value = RestConstants.ALL_PATH,
            method = {
                    RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET,
                    RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.TRACE
            }
    )
    public void api(HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String,String> testInfo = new HashMap<>();
        testInfo.put("A","good");
        request.setAttribute("info",testInfo);
        server.handle(request, response);


    }
}
