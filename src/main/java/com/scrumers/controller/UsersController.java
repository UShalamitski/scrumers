package com.scrumers.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.scrumers.model.Role;
import com.scrumers.model.User;
import com.scrumers.api.service.UserService;

@Controller
public class UsersController {

    @Autowired
    @Qualifier("userValidator")
    private Validator userValidator;
    @Autowired
    private UserService srv;
    @Autowired
    private UserQuickSearchState state;

    private ControllerUtils utils = new ControllerUtils();

    @RequestMapping("/users")
    public String users(final Model model) {
        List<User> users;
        if (state.getSearch() == null) {
            users = srv.getUsers();
        } else {
            users = srv.getUsers(state.getSearch());
            model.addAttribute("search", state.getSearch());
        }
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(final Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", srv.getRoles());
        return "user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, params = {"id"})
    public String user(@RequestParam(value = "id", required = true) final Long id, final Model model) {
        User user = srv.getUser(id);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("userRoles", srv.getUsersRoles(user.getId()));
        }
        List<Role> roles = srv.getRoles();
        model.addAttribute("roles", roles);
        return "user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(final Principal p,
                       @RequestParam(value = "file") final MultipartFile file,
                       @ModelAttribute("user") final User user,
                       final BindingResult result,
                       @RequestParam(value = "role", required = false) final Long[] rids,
                       final Model model) {

        if (file != null)
            user.setAvatar(file.getName());

        String errorRoles = "";
        List<Role> roles = srv.getRoles();
        List<Role> userRoles = new ArrayList<Role>();
        if (rids == null) {
            errorRoles = "roles.empty";
        } else {
            for (Role r : roles) {
                for (Long rid : rids)
                    if (r.getId() == rid) {
                        userRoles.add(r);
                    }
            }
        }

        if (p.getName() == null || p.getName().isEmpty()) {
            user.setIdCreator(new Long(0));
        } else {
            user.setIdCreator(srv.getUser(p.getName()).getId());
        }

        userValidator.validate(user, result);

        if (result.hasErrors() || rids == null) {
            model.addAttribute("user", user);
            model.addAttribute("roles", roles);
            model.addAttribute("userRoles", userRoles);
            model.addAttribute("errorRoles", errorRoles);
            return "user";
        }
        srv.saveUser(user, rids, file);

        return "redirect:/users.html";
    }

    @RequestMapping(value = "/deleteuser")
    public String delete(
            @RequestParam(value = "id", required = false) final Long[] id) {
        if (id != null) {
            srv.deleteUser(id);
        }
        return "redirect:/users.html";
    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.POST)
    public String search(
            @RequestParam(value = "search", required = false) final String search) {
        if (search == null || search.trim().isEmpty()) {
            state.setSearch(null);
        } else {
            state.setSearch(search);
        }
        return "redirect:/users.html";
    }

    @RequestMapping("/info")
    public String userInfo(final Principal p, final Model model, HttpSession session) {
        model.addAttribute("user", srv.getUser(p.getName()));
        session = utils.checkSessionAttr(session, srv.getUser(p.getName()), srv);
        return "user/userInfo";
    }

    @RequestMapping("/changePassword")
    public String chengePswd(final Principal p, final Model model, HttpSession session) {
        model.addAttribute("user", srv.getUser(p.getName()));
        session = utils.checkSessionAttr(session, srv.getUser(p.getName()), srv);
        return "user/changePswd";
    }

    @RequestMapping("/saveuserinfo")
    public String saveuserinfo(final Principal p,
                               @ModelAttribute("user") final User user,
                               final BindingResult result,
                               @RequestParam(value = "file") final MultipartFile file,
                               final Model model) {

        userValidator.validate(user, result);

        if (file != null)
            user.setAvatar(file.getName());

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "user/userInfo";
        }

        srv.saveUserInfo(file, user);
        return "redirect:/info.html";
    }

    @RequestMapping("/savepasswd")
    public String passwd(final String passwdOld, final String passwdConfirm,
                         @ModelAttribute("user") final User user,
                         final BindingResult result, final Model model) {

        String errorPasswd = "";
        user.setName(srv.getUser(user.getId()).getName());
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "user/changePswd";
        }

        if (!passwdConfirm.equals(user.getPasswd())) {
            model.addAttribute("user", user);
            errorPasswd = "psswd.invalidConfirm";
            model.addAttribute("errorConfirmed", errorPasswd);
            return "user/changePswd";
        }

        String tmp = user.getPasswd();
        user.setPasswd(passwdOld);
        if (srv.chekUserPasswd(user)) {
            user.setIdCreator(user.getId());
            user.setPasswd(tmp);
            srv.saveUserPasswd(user);
            model.addAttribute("messageSuccess", "messageSuccess.passwd");
        } else {
            errorPasswd = "passwd.invalid";
            model.addAttribute("errorPasswd", errorPasswd);
            return "user/changePswd";
        }

        return "redirect:/main.html";
    }

    @Component
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public static class UserQuickSearchState extends QuickSearchState {

        private static final long serialVersionUID = 1L;
    }

    @Component("userValidator")
    public static class UserValidator implements Validator {

        @Autowired
        private UserService srv;
        @Autowired
        private Configuration conf;

        @Override
        public boolean supports(final Class<?> clazz) {
            return User.class.equals(clazz);
        }

        @Override
        public void validate(final Object obj, final Errors errors) {

            User user = (User) obj;
            Pattern pattern;
            Matcher matcher;

            if (user.getId() == null) {
                if (srv.getUser(user.getName()) != null) {
                    errors.rejectValue("name", "login.exists");
                }
            } else {
                if (!(srv.getUser(user.getId()).getName()
                        .equals(user.getName()))
                        && srv.getUser(user.getName()) != null) {
                    errors.rejectValue("name", "login.exists");
                }
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                    "login.empty");

            if (user.getPasswd() != null) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwd",
                        "passwd.empty");

                pattern = Pattern.compile(conf.getString("userPasswdPattern"));
                matcher = pattern.matcher(user.getPasswd());
                if (!matcher.matches()) {
                    errors.rejectValue("passwd", "passwd.error");
                }
            }

            if (user.getFname() != null) {
                pattern = Pattern.compile(conf.getString("userNamePattern"));
                matcher = pattern.matcher(user.getFname());
                if (!matcher.matches()) {
                    errors.rejectValue("fname", "fname.invalid");
                }
            }

            if (user.getSname() != null) {
                pattern = Pattern.compile(conf.getString("userNamePattern"));
                matcher = pattern.matcher(user.getSname());
                if (!matcher.matches()) {
                    errors.rejectValue("sname", "sname.invalid");
                }
            }

            if (user.getName() != null) {
                pattern = Pattern.compile(conf.getString("userNamePattern"));
                matcher = pattern.matcher(user.getName());
                if (!matcher.matches()) {
                    errors.rejectValue("name", "login.invalid");
                }
            }

            if (user.getEmail() != null) {
                pattern = Pattern.compile(conf.getString("userEmail"));
                matcher = pattern.matcher(user.getEmail());
                if (!matcher.matches()) {
                    errors.rejectValue("email", "user.email.invalid");
                }
            }
        }
    }
}
