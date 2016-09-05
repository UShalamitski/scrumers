package com.scrumers.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scrumers.model.Organization;
import com.scrumers.model.User;
import com.scrumers.api.service.IterationService;
import com.scrumers.api.service.OrganizationService;
import com.scrumers.api.service.ProductService;
import com.scrumers.api.service.UserService;

@Controller
public class OrganizationController {

    @Autowired
    private UserService userSrv;

    @Autowired
    private ProductService productSrv;

    @Autowired
    private IterationService iterationSrv;

    @Autowired
    private OrganizationService orgSrv;

    private ControllerUtils utils = new ControllerUtils();

    private OrganizationValidator validator = new OrganizationValidator();

    @RequestMapping("/organization_my")
    public String myOrganizations(final Principal p, final Model model,
            HttpSession session) {

        List<Organization> organizations;
        User u = userSrv.getUser(p.getName());
        organizations = orgSrv.getOrganizationsByUserId(u.getId());
        model.addAttribute("organizations", organizations);
        model.addAttribute("principal", u.getId());
        model.addAttribute("organizationModel", new Organization());
        session = utils.checkSessionAttr(session, u, userSrv);
        session.setAttribute("mode", "my");
        return "organization/organizations";
    }

    @RequestMapping("/organization_all")
    public String organizations(final Principal p, final Model model,
            HttpSession session) {

        List<Organization> organizations;
        User u = userSrv.getUser(p.getName());
        organizations = orgSrv.getOrganizations();
        model.addAttribute("organizations", organizations);
        model.addAttribute("principal", u.getId());
        model.addAttribute("organizationModel", new Organization());
        session = utils.checkSessionAttr(session, u, userSrv);
        session.setAttribute("mode", "all");
        return "organization/organizations";
    }

    @RequestMapping("/organization_new")
    public String newOrganization(final Principal p, final Model model,
            HttpSession session) {
        model.addAttribute("organization", new Organization());
        session = utils.checkSessionAttr(session, userSrv.getUser(p.getName()),
                userSrv);
        return "organization/newOrganization";
    }

    @RequestMapping("/organization_save")
    public String saveOrganization(final Principal p, HttpSession session,
            @ModelAttribute("organization") final Organization organization,
            final BindingResult result, Model model) {
        organization.setIdCreator(userSrv.getUser(p.getName()).getId());
        
        validator.validate(organization, result); // validation

        if (result.hasErrors()) {
            model.addAttribute("organization", organization);
            return "organization/newOrganization";
        } // end validation
        
        orgSrv.saveOrganizationWithUserId(organization);

        if (session.getAttribute("mode") != null) {
            if (session.getAttribute("mode").equals("my")) {
                return "redirect:organization_my.html";
            } else {
                return "redirect:organization_all.html";
            }
        }
        return "redirect:organization_my.html";

    }

    @RequestMapping("/organization_switch")
    public String switchOrganization(
            @RequestParam(value = "oid", required = true) final Long oid,
            final Principal p, HttpSession session,
            @ModelAttribute("organization") final Organization organization) {
        User u = userSrv.getUser(p.getName());
        u.setActualOrganization(oid);
        u.setActualProduct(0L);
        u.setActualIteration(0L);
        userSrv.saveUser(u);
        session = utils.delAttributes(session);
        return "redirect:main.html";
    }

    @RequestMapping(value = "/organization_delete")
    public String deleteOrganization(
            @RequestParam(value = "id", required = false) final Long id,
            final Principal p, HttpSession session) {
        if (id != null) {
            User u = userSrv.getUser(p.getName());

            if (u.getActualOrganization() == id) {
                u.setActualOrganization(0L);
                u.setActualProduct(0L);
                u.setActualIteration(0L);
                userSrv.saveUser(u);
                session = utils.delAttributes(session);
            }

            Long[] idd = { id };

            Organization o = orgSrv.getOrganization(id);

            if (o.getIdCreator() == u.getId()) {
                orgSrv.deleteOrganizationAsAnOwner(idd);
            } else {
                orgSrv.deleteOrganizationAsAMember(u.getId(), id);
            }
        }
        if (session.getAttribute("mode").equals("my")) {
            return "redirect:organization_my.html";
        } else {
            return "redirect:organization_all.html";
        }
    }

    public static class OrganizationValidator implements Validator {

        @Override
        public boolean supports(final Class<?> clazz) {
            return Organization.class.equals(clazz);
        }

        @Override
        public void validate(final Object obj, final Errors errors) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                    "iteration.name.empty");
        }
    }

}
