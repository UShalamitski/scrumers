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
    private UserService srv;

    @Autowired
    private IterationService iterSrv;

    @Autowired
    private ProductService prodSrv;
    
    @Autowired
    private Configuration conf;

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
    public void image(final Long id, final HttpServletRequest req,
            final HttpServletResponse res) {

        InputStream is = srv.getImage(id);

        if (is == null) {
            is = getNotImage(req);
        }

        if (is == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            try {
                res.addHeader("Content-Type",
                        "image/" + conf.getString("imageFormate"));
                OutputStream os = res.getOutputStream();
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
    public void func1(
            final HttpServletRequest req, final HttpServletResponse res, Principal p) {
        try {
            Long iid = srv.getUser(p.getName()).getActualIteration();
            res.addHeader("Content-Type", "application/json");
            List<PlotData> list = iterSrv.getIterationPlotData(iid);
            PlotData[] pd = list.toArray(new PlotData[list.size()]);
            Gson gson = GsonUtils.createGson();
            OutputStreamWriter osw = new OutputStreamWriter(
                    res.getOutputStream());
            gson.toJson(pd, osw);
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @RequestMapping("/plot2")
    public void func(
            final HttpServletRequest req, final HttpServletResponse res, Principal p) {
        try {
            res.addHeader("Content-Type", "application/json");
            
            Long pid = srv.getUser(p.getName()).getActualProduct();
            List<PlotData> list2 = prodSrv.getProductPlotData1(pid);
            
            PlotData[] pd = list2.toArray(new PlotData[list2.size()]);
            Gson gson = GsonUtils.createGson();
            OutputStreamWriter osw = new OutputStreamWriter(
                    res.getOutputStream());
            gson.toJson(pd, osw);
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/plot3")
    public void func3(
            final HttpServletRequest req, final HttpServletResponse res, Principal p) {
        try {
            res.addHeader("Content-Type", "application/json");
            
            Long pid = srv.getUser(p.getName()).getActualProduct();
            List<PlotData> list2 = prodSrv.getProductPlotData2(pid);
            
            PlotData[] pd = list2.toArray(new PlotData[list2.size()]);
            Gson gson = GsonUtils.createGson();
            OutputStreamWriter osw = new OutputStreamWriter(
                    res.getOutputStream());
            gson.toJson(pd, osw);
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
