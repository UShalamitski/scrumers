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

import com.scrumers.model.Team;
import com.scrumers.model.TeamMember;
import com.scrumers.model.User;
import com.scrumers.api.service.TeamService;
import com.scrumers.api.service.UserService;

@Controller
public class TeamController {

    @Autowired
    private UserService userSrv;

    @Autowired
    private TeamService teamSrv;

    private ControllerUtils utils = new ControllerUtils();

    private TeamValidator validator = new TeamValidator();

    @RequestMapping("/team_my")
    public String teamMy(
            final Principal p,
            final Model model,
            @RequestParam(value = "tid", required = false) final Long tid,
            @RequestParam(value = "table", required = false) final boolean table,
            HttpSession session) {

        List<Team> teams = null;
        User u = userSrv.getUser(p.getName());
        teams = teamSrv.getTeamsByUserIdAndOrganizationId(u.getId(),
                u.getActualOrganization());
        session = utils.checkSessionAttr(session, u, userSrv);
        session.setAttribute("mode", "my");

        if (!table) {
            List<TeamMember> users = null;
            Team active_team = null;

            if (teams.size() > 0) {
                if (tid != null) {
                    users = userSrv.getUsersByTeam(tid);
                    active_team = teamSrv.getTeam(tid);
                } else {
                    users = userSrv.getUsersByTeam(teams.get(0).getId());
                    active_team = teams.get(0);
                }
            }

            model.addAttribute("roles", userSrv.getTeamRoles());
            model.addAttribute("active_team", active_team);
            model.addAttribute("users", users);
            model.addAttribute("teams", teams);
            model.addAttribute("principal", u.getId());
            model.addAttribute("teamModel", new Team());
            return "team/teams";
        } else {
            model.addAttribute("teams", teams);
            model.addAttribute("principal", u.getId());
            model.addAttribute("teamModel", new Team());
            return "team/teamsTable";
        }
    }

    @RequestMapping("/team_all")
    public String teamAll(
            final Principal p,
            final Model model,
            @RequestParam(value = "tid", required = false) final Long tid,
            @RequestParam(value = "table", required = false) final boolean table,
            HttpSession session) {

        List<Team> teams = null;
        User u = userSrv.getUser(p.getName());
        teams = teamSrv.getTeams();
        session = utils.checkSessionAttr(session, u, userSrv);
        session.setAttribute("mode", "all");

        if (!table) {
            List<TeamMember> users = null;
            Team active_team = null;

            if (teams.size() > 0) {
                if (tid != null) {
                    users = userSrv.getUsersByTeam(tid);
                    active_team = teamSrv.getTeam(tid);
                } else {
                    users = userSrv.getUsersByTeam(teams.get(0).getId());
                    active_team = teams.get(0);
                }
            }

            model.addAttribute("roles", userSrv.getTeamRoles());
            model.addAttribute("active_team", active_team);
            model.addAttribute("users", users);
            model.addAttribute("teams", teams);
            model.addAttribute("principal", u.getId());
            model.addAttribute("teamModel", new Team());
            model.addAttribute("mode", "all");
            return "team/teams";
        } else {
            model.addAttribute("teams", teams);
            model.addAttribute("principal", u.getId());
            model.addAttribute("teamModel", new Team());
            return "team/teamsTable";
        }
    }

    @RequestMapping("/team_save")
    public String saveTeam(final Principal p, HttpSession session,
            @ModelAttribute("teamModel") final Team team,
            final BindingResult result, Model model,
            @RequestParam(value = "table", required = false) final boolean table) {

        User u = userSrv.getUser(p.getName());
        team.setIdCreator(u.getId());

         validator.validate(team, result); // validation
         if (result.hasErrors()) {
         model.addAttribute("team", team);
         model.addAttribute("table", table);
         return "team/team";
         } // end validation

        teamSrv.saveTeam(team, u.getActualOrganization(), 1L);
        if (session.getAttribute("mode").equals("my")) {
            if (!table)
                return "redirect:team_my.html";
            else
                return "redirect:team_my.html?table=true";
        } else {
            if (!table)
                return "redirect:team_all.html";
            else
                return "redirect:team_all.html?table=true";
        }
    }

    @RequestMapping(value = "/team_delete")
    public String deleteTeam(
            @RequestParam(value = "id", required = false) final Long id,
            @RequestParam(value = "table", required = false) final boolean table,
            final Principal p, HttpSession session) {
        if (id != null) {
            Long[] idd = { id };
            User u = userSrv.getUser(p.getName());
            Team t = teamSrv.getTeam(id);
            if (t.getIdCreator() == u.getId()) {
                teamSrv.deleteTeamByOwner(idd);
            } else {
                teamSrv.deleteTeamByMember(u.getId(), id);
            }
        }
        if (session.getAttribute("mode").equals("my")) {
            if (!table)
                return "redirect:team_my.html";
            else
                return "redirect:team_my.html?table=true";
        } else {
            if (!table)
                return "redirect:team_all.html";
            else
                return "redirect:team_all.html?table=true";
        }
    }

    @RequestMapping("/addMember")
    public String addMember(
            final Principal p,
            final Model model,
            @RequestParam(value = "uid", required = false) Long uid,
            @RequestParam(value = "uLogin", required = false) final String login,
            @RequestParam("rid") Long rid, @RequestParam("tid") Long tid,
            HttpSession session) {

        if (login != null) {
            User u = userSrv.getUser(login);
            if (u != null)
                uid = u.getId();
        }

        if (uid != null && tid != null && rid != null) {
            teamSrv.addUserToATeam(uid, tid, rid);
        } else {
            if (session.getAttribute("mode").equals("my")) {
                return "redirect:team_my.html";
            } else {
                return "redirect:team_all.html";
            }
        }
        if (session.getAttribute("mode").equals("my")) {
            return "redirect:team_my.html?tid=" + tid;
        } else {
            return "redirect:team_all.html?tid=" + tid;
        }

    }

    @RequestMapping("/deleteMember")
    public String deleteMember(final Principal p, final Model model,
            @RequestParam("id") final Long uid, @RequestParam("tid") Long tid,
            HttpSession session) {

        if (uid != null && tid != null) {
            teamSrv.deleteMemberFromTeam(uid, tid);

            if (session.getAttribute("mode").equals("my")) {
                return "redirect:team_my.html?tid=" + tid;
            } else {
                return "redirect:team_all.html?tid=" + tid;
            }
        }

        if (session.getAttribute("mode").equals("my")) {
            return "redirect:team_my.html";
        } else {
            return "redirect:team_all.html";
        }
    }

    public static class TeamValidator implements Validator {

        @Override
        public boolean supports(final Class<?> clazz) {
            return Team.class.equals(clazz);
        }

        @Override
        public void validate(final Object obj, final Errors errors) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                    "iteration.name.empty");
        }
    }

}
