package com.scrumers.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scrumers.model.User;
import com.scrumers.api.service.UserService;

@Controller
public class MainController {

    @Autowired
    private UserService userSrv;

    private ControllerUtils utils = new ControllerUtils();

    @RequestMapping("/main")
    public String main(final Principal p, Model model,
            HttpSession session, HttpServletRequest req, 
            @RequestParam(value = "lang", required = false) final String lang) {

        User u = userSrv.getUser(p.getName());

        // if (u.isFirst()) {
        // u.setFirst(false);
        // userSrv.saveUser(u);
        // return "redirect:/organization_new.html";
        // } else {

        session = utils.checkSessionAttr(session, u, userSrv);
        session.setAttribute("lang", lang);

        if (utils.IsSessionAttrEqNull(session)) {
            return "redirect:/organization_my.html";
        }
        if (utils.IsAllSessionAttrsExists(session)) {
            return "redirect:/iteration_Burndown.html";
        }

        model.addAttribute("active_item", "scrum");

        return "main/main";
    }

    @RequestMapping("/main_wiki")
    public String mainWiki(final Principal p, final Model model,
            @RequestParam(value = "item", required = false) String item,
            HttpSession session) {

        User u = userSrv.getUser(p.getName());
        session = utils.checkSessionAttr(session, u, userSrv);

        if (item == null)
            item = "scrum";

        model.addAttribute("active_item", item);
        return "main/main";
    }

}
