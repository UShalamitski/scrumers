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

import com.scrumers.model.Product;
import com.scrumers.model.ProductView;
import com.scrumers.model.Role;
import com.scrumers.model.Story;
import com.scrumers.model.Team;
import com.scrumers.model.User;
import com.scrumers.api.service.IterationService;
import com.scrumers.api.service.ProductService;
import com.scrumers.api.service.StoryService;
import com.scrumers.api.service.TeamService;
import com.scrumers.api.service.UserService;

@Controller
public class ProductController {

    @Autowired
    private UserService userSrv;

    @Autowired
    private ProductService productSrv;

    @Autowired
    private TeamService teamSrv;

    @Autowired
    private StoryService storySrv;

    @Autowired
    private IterationService iterationSrv;

    private ControllerUtils utils = new ControllerUtils();

    private ProductValidator validator = new ProductValidator();

    @RequestMapping(value = "/productDiagram")
    public String burndownDiagram(final Principal p, final Model model,
            HttpSession session) {
        Long pid = userSrv.getUser(p.getName()).getActualProduct();
        Product product = productSrv.getProduct(pid);
        List<Story> stories = storySrv.getStoriesByProductId(pid);

        int st1 = 0;
        int st2 = 0;
        int st3 = 0;
        int st4 = 0;

        for (Story s : stories) {
            if (s.getStatusId() == 1)
                st1++;
            else if (s.getStatusId() == 2)
                st2++;
            else if (s.getStatusId() == 3)
                st3++;
            else if (s.getStatusId() == 4)
                st4++;
        }

        model.addAttribute("st1", st1);
        model.addAttribute("st2", st2);
        model.addAttribute("st3", st3);
        model.addAttribute("st4", st4);
        model.addAttribute("name", product.getName());

        session = utils.checkSessionAttr(session, userSrv.getUser(p.getName()),
                userSrv);
        return "main/diagramProduct";
    }

    @RequestMapping("/product_my")
    public String myProducts(
            final Principal p,
            final Model model,
            @RequestParam(value = "pid", required = false) final Long pid,
            @RequestParam(value = "table", required = false) final boolean table,
            HttpSession session) {

        List<ProductView> products = null;
        User u = userSrv.getUser(p.getName());
        products = productSrv.getProductsViewByUserIdAndOrganizationId(
                u.getId(), u.getActualOrganization());
        session = utils.checkSessionAttr(session, u, userSrv);
        session.setAttribute("mode", "my");

        if (!table) {
            Product active_product = null;
            List<Team> teams = null;
            List<Team> all_teams = null;

            all_teams = teamSrv.getTeamsByUserIdAndOrganizationId(u.getId(),
                    u.getActualOrganization());

            if (products.size() > 0) {
                if (pid != null) {
                    teams = teamSrv.getByProductId(pid);
                    active_product = productSrv.getProduct(pid);
                } else {
                    teams = teamSrv.getByProductId(products.get(0).getId());
                    active_product = products.get(0);
                }
            }

            model.addAttribute("all_teams", all_teams);
            model.addAttribute("active_product", active_product);
            model.addAttribute("teams", teams);
            model.addAttribute("products", products);
            model.addAttribute("principal", u.getId());
            model.addAttribute("productModel", new Product());
            model.addAttribute("teamModel", new Team());
            return "product/products";
        } else {
            model.addAttribute("products", products);
            model.addAttribute("principal", u.getId());
            model.addAttribute("productModel", new Product());
            return "product/productsTable";
        }
    }

    @RequestMapping("/product_all")
    public String products(
            final Principal p,
            final Model model,
            @RequestParam(value = "pid", required = false) final Long pid,
            @RequestParam(value = "table", required = false) final boolean table,
            HttpSession session) {

        List<ProductView> products = null;
        User u = userSrv.getUser(p.getName());
        products = productSrv.getProductsView();
        session = utils.checkSessionAttr(session, u, userSrv);
        session.setAttribute("mode", "all");

        if (!table) {
            Product active_product = null;
            List<Team> teams = null;
            List<Team> all_teams = null;
            all_teams = teamSrv.getTeams();

            if (products.size() > 0) {
                if (pid != null) {
                    teams = teamSrv.getByProductId(pid);
                    active_product = productSrv.getProduct(pid);
                } else {
                    teams = teamSrv.getByProductId(products.get(0).getId());
                    active_product = products.get(0);
                }
            }

            model.addAttribute("all_teams", all_teams);
            model.addAttribute("active_product", active_product);
            model.addAttribute("teams", teams);
            model.addAttribute("products", products);
            model.addAttribute("principal", u.getId());
            model.addAttribute("productModel", new Product());
            model.addAttribute("teamModel", new Team());

            session = utils.checkSessionAttr(session, u, userSrv);
            session.setAttribute("mode", "all");
            return "product/products";
        } else {
            model.addAttribute("products", products);
            model.addAttribute("principal", u.getId());
            model.addAttribute("productModel", new Product());
            return "product/productsTable";
        }
    }

    @RequestMapping("/product_switch")
    public String switchOrganization(
            @RequestParam(value = "pid", required = true) final Long pid,
            final Principal p, HttpSession session,
            @ModelAttribute("product") final Product product) {
        User u = userSrv.getUser(p.getName());
        u.setActualProduct(pid);
        u.setActualIteration(0L);
        userSrv.saveUser(u);
        session = utils.delAttributes(session);

        if (session.getAttribute("mode").equals("all"))
            return "redirect:product_all.html?pid=" + pid;
        else
            return "redirect:product_my.html?pid=" + pid;
    }

    @RequestMapping("/product_addTeam")
    public String switchOrganization1(
            @RequestParam(value = "tid", required = true) final Long tid,
            @RequestParam(value = "pid", required = true) final Long pid,
            HttpSession session) {

        productSrv.addNewTeam(pid, tid);
        if (session.getAttribute("mode").equals("all"))
            return "redirect:product_all.html?pid=" + pid;
        else
            return "redirect:product_my.html?pid=" + pid;
    }

    @RequestMapping("/product_deleteTeam")
    public String productDeleteTeam(
            @RequestParam(value = "id", required = true) final Long tid,
            @RequestParam(value = "id2", required = true) final Long pid,
            HttpSession session) {
        productSrv.deleteTeamFromProduct(tid, pid);
        if (session.getAttribute("mode").equals("all"))
            return "redirect:product_all.html?pid=" + pid;
        else
            return "redirect:product_my.html?pid=" + pid;
    }

    @RequestMapping("/product_saveTeam")
    public String saveTeam(final Principal p,
            @RequestParam(value = "pid", required = true) final Long pid,
            @ModelAttribute("team") final Team team, HttpSession session) {
        User u = userSrv.getUser(p.getName());
        team.setIdCreator(u.getId());

        teamSrv.saveTeam(team, u.getActualOrganization(), 1L);
        if (session.getAttribute("mode").equals("all"))
            return "redirect:product_all.html?pid=" + pid;
        else
            return "redirect:product_my.html?pid=" + pid;
    }

    @RequestMapping("/product_save")
    public String saveProduct(final Principal p, HttpSession session,
            @ModelAttribute("product") final Product product,
            final BindingResult result, Model model,
            @RequestParam(value = "table", required = false) final boolean table) {

        User u = userSrv.getUser(p.getName());
        product.setIdCreator(u.getId());
        product.setOrganizationId(u.getActualOrganization());

        validator.validate(product, result); // validation
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("table", table);
            return "product/product";
        } // end validation

        productSrv.saveProduct(product);

        if (session.getAttribute("mode").equals("my")) {
            if (!table) {
                return "redirect:product_my.html";
            } else {
                return "redirect:product_my.html?table=true";
            }
        } else {
            if (!table) {
                return "redirect:product_all.html";
            } else {
                return "redirect:product_all.html?table=true";
            }
        }
    }

    @RequestMapping("/product_change_owner")
    public String changeOwnerProduct(
            final Principal p,
            final Model model,
            @RequestParam(value = "prod", required = false) final Long pid,
            @RequestParam(value = "userName", required = false) final String userName,
            HttpSession session) {
        User owner = userSrv.getUser(userName);
        if (owner != null) {
            productSrv.addedProductOwner(pid, owner.getId());
        } else {
            List<ProductView> products;
            User u = userSrv.getUser(p.getName());
            products = productSrv.getProductsViewByUserIdAndOrganizationId(
                    u.getId(), u.getActualOrganization());
            model.addAttribute("products", products);
            model.addAttribute("principal", u.getId());
            model.addAttribute("productModel", new Product());
            model.addAttribute("errorUserName", "userName.error");
            return "product/products";
        }
        if (session.getAttribute("mode").equals("my"))
            return "redirect:product_my.html";
        else
            return "redirect:product_all.html";
    }

    @RequestMapping(value = "/product_delete")
    public String deleteProduct(
            @RequestParam(value = "id", required = false) final Long id,
            @RequestParam(value = "table", required = false) final boolean table,
            final Principal p, HttpSession session) {

        User principal = userSrv.getUser(p.getName());
        List<Role> principalRoles = userSrv.getUsersRoles(principal.getId());
        boolean flag = false;

        if (id != null) {
            User u = userSrv.getUser(p.getName());

            if (u.getActualProduct() == id) {
                u.setActualProduct(0L);
                u.setActualIteration(0L);
                userSrv.saveUser(u);
                session = utils.delAttributes(session);
            }

            if (productSrv.getProduct(id).getIdCreator() == u.getId()) {
                productSrv.deleteProductByOwner(id);
            } else {
                productSrv.deleteProductByMember(id, u.getId());
            }

            for (Role role : principalRoles) {
                flag = (role.getId() == 1);
                if (flag)
                    break;
            }
            if (flag) {
                productSrv.deleteProduct(id);
            } else {
                Long[] idd = { id };
                productSrv.deleteProductByOwner(idd);
            }

        }
        if (session.getAttribute("mode").equals("my")) {
            if (!table) {
                return "redirect:product_my.html";
            } else {
                return "redirect:product_my.html?table=true";
            }
        } else {
            if (!table) {
                return "redirect:product_all.html";
            } else {
                return "redirect:product_all.html?table=true";
            }
        }
    }

    public static class ProductValidator implements Validator {

        @Override
        public boolean supports(final Class<?> clazz) {
            return Product.class.equals(clazz);
        }

        @Override
        public void validate(final Object obj, final Errors errors) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                    "iteration.name.empty");
        }
    }

}
