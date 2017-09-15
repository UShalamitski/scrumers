package com.scrumers.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.scrumers.model.PlotData;
import com.scrumers.api.service.IterationService;
import com.scrumers.api.service.ProductService;
import com.scrumers.api.service.UserService;
import com.scrumers.util.GsonUtils;

@Controller
public class AvatarController {

    @Autowired
    private UserService userService;
    @Autowired
    private IterationService iterationService;
    @Autowired
    private ProductService productService;
    @Autowired
    private Configuration configuration;

    private InputStream getNotImage(final HttpServletRequest req) {
        InputStream is = null;
        String name = req.getServletContext().getRealPath("/images/user.png");

        try {
            is = new FileInputStream(name);
        } catch (FileNotFoundException e) {
        }

        return is;
    }

    @RequestMapping("/avatar")
    public void image(final Long id, final HttpServletRequest request, final HttpServletResponse response) {
        InputStream is = userService.getImage(id);

        if (is == null) {
            is = getNotImage(request);
        }

        if (is == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            try {
                response.setContentType("image/" + configuration.getString("imageFormate"));
                OutputStream os = response.getOutputStream();
                IOUtils.copy(is, os);
                os.flush();
            } catch (IOException e) {
            } finally {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }
    }

    @RequestMapping("/plot")
    public void func1(final HttpServletRequest request, final HttpServletResponse response, Principal principal) {
        try {
            Long actualIterationId = userService.getUser(principal.getName()).getActualIteration();
            response.setContentType("application/json");
            List<PlotData> list = iterationService.getIterationPlotData(actualIterationId);
            PlotData[] pd = list.toArray(new PlotData[list.size()]);
            Gson gson = GsonUtils.createGson();
            OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream());
            gson.toJson(pd, osw);
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/plot2")
    public void func(final HttpServletRequest request, final HttpServletResponse response, Principal principal) {
        try {
            response.setContentType("application/json");
            
            Long actualProductId = userService.getUser(principal.getName()).getActualProduct();
            List<PlotData> list2 = productService.getProductPlotData1(actualProductId);
            
            PlotData[] pd = list2.toArray(new PlotData[list2.size()]);
            Gson gson = GsonUtils.createGson();
            OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream());
            gson.toJson(pd, osw);
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/plot3")
    public void func3(final HttpServletRequest request, final HttpServletResponse response, Principal principal) {
        try {
            response.setContentType("application/json");
            
            Long actualProductId = userService.getUser(principal.getName()).getActualProduct();
            List<PlotData> list2 = productService.getProductPlotData2(actualProductId);
            
            PlotData[] pd = list2.toArray(new PlotData[list2.size()]);
            Gson gson = GsonUtils.createGson();
            OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream());
            gson.toJson(pd, osw);
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
