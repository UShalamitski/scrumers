package com.scrumers.controller;

import com.scrumers.api.service.IterationService;
import com.scrumers.api.service.ProductService;
import com.scrumers.api.service.StoryService;
import com.scrumers.api.service.UserService;
import com.scrumers.model.Iteration;
import com.scrumers.model.Story;
import com.scrumers.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class IterationController {

    @Autowired
    private UserService userSrv;

    @Autowired
    private ProductService productSrv;

    @Autowired
    private IterationService iterationSrv;

    @Autowired
    private StoryService storySrv;

    private IterationValidator iterationValidator = new IterationValidator();

    private StoryValidator storyValidator = new StoryValidator();

    private ControllerUtils utils = new ControllerUtils();

    private Model getInformationForIterations(final Principal p,
            final Model model, HttpSession session, final Long iid,
            final boolean table) {
        List<Iteration> iterations = null;
        User u = null;
        u = userSrv.getUser(p.getName());
        iterations = iterationSrv
                .getIterationsByProductId(u.getActualProduct());

        if (!table) {
            Iteration active_iteration = null;
            List<Story> stories = null;
            List<User> users = null;

            users = userSrv.readUsersByProductId(u.getActualProduct());

            if (iterations.size() > 0) {
                if (iid != null) {
                    stories = storySrv.getStoriesByIterationId(iid);
                    active_iteration = iterationSrv.getIteration(iid);
                } else {
                    stories = storySrv.getStoriesByIterationId(iterations
                            .get(0).getId());
                    active_iteration = iterations.get(0);
                }
            }

            model.addAttribute("iterationModel", new Iteration());
            model.addAttribute("users", users);
            model.addAttribute("active_iteration", active_iteration);
            model.addAttribute("stories", stories);
            model.addAttribute("iterations", iterations);
            model.addAttribute("principal", u.getId());
            model.addAttribute("storyModel", new Story());
            return model;
        } else {
            model.addAttribute("iterations", iterations);
            model.addAttribute("iterationModel", new Iteration());
            return model;
        }
    }

    @RequestMapping("/iterations")
    public String iterations(final Principal p, Model model,
            HttpSession session,
            @RequestParam(value = "iid", required = false) final Long iid,
            @RequestParam(value = "table", required = false) final boolean table) {

        User u = null;

        System.out.println(p.getName());
        u = userSrv.getUser(p.getName());
        model = getInformationForIterations(p, model, session, iid, table);
        model.addAttribute("actionComment", "commentStoryIteration.html");
        if (!table) {
            session = utils.checkSessionAttr(session, u, userSrv);
            return "iteration/iterations";
        } else {
            session = utils.checkSessionAttr(session, u, userSrv);
            return "iteration/iterationsTable";
        }
    }

    @RequestMapping("/iteration_save")
    public String saveIteration(final Principal p, HttpSession session,
                                @ModelAttribute("iterationModel") final Iteration iteration,
                                final BindingResult result, Model model,
                                @RequestParam("sdate") String sdate,
                                @RequestParam("edate") String edate,
                                @RequestParam(value = "table", required = false) final boolean table) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date s_date = null;
        java.util.Date e_date = null;
        try {
            if (sdate != "")
                s_date = sdf.parse(sdate);
            if (edate != "")
                e_date = sdf.parse(edate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User u = userSrv.getUser(p.getName());
        iteration.setDateEnd(e_date);
        iteration.setDateStart(s_date);
        iteration.setIdCreator(u.getId());
        iteration.setProductId(u.getActualProduct());

        iterationValidator.validate(iteration, result); // validation

        if (result.hasErrors()) {
            if (iteration.getDateStart() != null)
                model.addAttribute("date1", sdf.format(iteration.getDateStart()));
            if (iteration.getDateEnd() != null)
                model.addAttribute("date2", sdf.format(iteration.getDateEnd()));
            model.addAttribute("iterationModel", iteration);
            model.addAttribute("table", table);
            return "iteration/iteration";
        } // end validation

        iterationSrv.saveIteration(iteration);

        if (!table) {
            return "redirect:iterations.html?iid=" + u.getActualIteration();
        } else {
            return "redirect:iterations.html?table=true";
        }
    }

    @RequestMapping("/iteration_switch")
    public String iterationSwitch(
            @RequestParam(value = "iid", required = true) final Long iid,
            final Principal p, HttpSession session) {
        User u = userSrv.getUser(p.getName());
        u.setActualIteration(iid);
        userSrv.saveUser(u);
        session = utils.delAttributes(session);
        return "redirect:iterations.html?iid=" + iid;
    }

    @RequestMapping(value = "/iteration_deleteStory", method = RequestMethod.POST)
    public String iterationDeleteStory(
            @RequestParam(value = "id", required = false) final Long sid,
            final Principal p) {
        storySrv.deleteStoryFromIteration(sid);
        Long iid = userSrv.getUser(p.getName()).getActualIteration();
        return "redirect:iterations.html?iid=" + iid;
    }

    @RequestMapping("/iteration_saveStory")
    public String iterationSaveStory(final Principal p, final Model model,
            @ModelAttribute("storyModel") final Story story,
            final BindingResult result) {
        User u = userSrv.getUser(p.getName());
        story.setIdCreator(u.getId());

        storyValidator.validate(story, result);

        if (result.hasErrors()) {
            List<User> users = null;
            users = userSrv.readUsersByProductId(u.getActualProduct());

            model.addAttribute("saveAction", "iteration_saveStory.html");
            model.addAttribute("users", users);
            model.addAttribute("assignee", story.getAssignee());
            model.addAttribute("storyModel", story);
            return "story/new_story";
        }

        storySrv.saveStory(story, u.getActualProduct(), u.getActualIteration());
        return "redirect:iterations.html?iid=" + u.getActualIteration();
    }

    @RequestMapping(value = "/iteration_delete")
    public String deleteIteration(
            @RequestParam(value = "id", required = false) final Long id,
            @RequestParam(value = "table", required = false) final boolean table,
            final Principal p, HttpSession session) {

        iterationSrv.deleteIteration(id);
        User u = userSrv.getUser(p.getName());

        if (u.getActualIteration() == id) {
            u.setActualIteration(0L);
            userSrv.saveUser(u);
            session = utils.delAttributes(session);
        }

        if (!table) {
            return "redirect:iterations.html";
        } else {
            return "redirect:iterations.html?table=true";
        }
    }

    @RequestMapping(value = "/iteration_delStory", method = RequestMethod.POST)
    public String iteration_delStory(
            @RequestParam(value = "id", required = false) final Long sid) {
        storySrv.deleteStoryFromIteration(sid);
        return "redirect:iteration_storyList.html";
    }

    @RequestMapping("/iteration_planing")
    public String iterationPlaning(final Principal p, final Model model,
            HttpSession session) {

        List<Story> stories_b;
        List<Story> stories_i;
        User u = userSrv.getUser(p.getName());
        List<User> users = userSrv.readUsersByProductId(u.getActualProduct());

        stories_b = storySrv.getUnusedStoriesFromBacklog(u.getActualProduct());
        stories_i = storySrv.getStoriesByIterationId(u.getActualIteration());

        model.addAttribute("users", users);
        model.addAttribute("stories_b", stories_b);
        model.addAttribute("stories_i", stories_i);
        session = utils.checkSessionAttr(session, u, userSrv);
        model.addAttribute("storyModel", new Story());
        model.addAttribute("actionComment", "commentStoryIterationPlaning.html");
        return "iteration/iteration_planing";
    }

    @RequestMapping("/iteration_planing_save_story")
    public String iterationPlaningSave(final Principal p,
            @ModelAttribute("storyModel") final Story story,
            final BindingResult result, final Model model) {
        User u = userSrv.getUser(p.getName());
        story.setIdCreator(u.getId());

        storyValidator.validate(story, result);

        if (result.hasErrors()) {
            List<User> users = null;
            users = userSrv.readUsersByProductId(u.getActualProduct());

            model.addAttribute("saveAction", "iteration_saveStory.html");
            model.addAttribute("users", users);
            model.addAttribute("saveAction",
                    "iteration_planing_save_story.html");
            model.addAttribute("storyModel", story);
            return "story/new_story";
        }

        storySrv.saveStory(story, u.getActualProduct(), null);
        return "redirect:iteration_planing.html";
    }

    @RequestMapping(value = "/iteration_planning_del_from_backlog", method = RequestMethod.POST)
    public String iterationPlanningDel(
            @RequestParam(value = "id", required = false) final Long id) {
        storySrv.deleteStory(id);
        return "redirect:iteration_planing.html";
    }

    @RequestMapping(value = "/iteration_planning_del_from_iter", method = RequestMethod.POST)
    public String iterationPlanningDelIter(
            @RequestParam(value = "id", required = false) final Long sid) {
        storySrv.deleteStoryFromIteration(sid);
        return "redirect:iteration_planing.html";
    }

    
    @RequestMapping(value = "/iteration_Burndown")
    public String burndownDiagram(final Principal p, final Model model,
            HttpSession session) {
        Long iid = userSrv.getUser(p.getName()).getActualIteration();
        Iteration iteration = iterationSrv.getIteration(iid);
        List<Story> stories = storySrv.getStoriesByIterationId(iid);

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
        model.addAttribute("iteration", iteration);

        session = utils.checkSessionAttr(session, userSrv.getUser(p.getName()),
                userSrv);
        return "main/diagramBurndown";
    }
    
    @RequestMapping(value = "/iteration_storyList")
    public String iterationsList(final Principal p, final Model model,
            HttpSession session) {
        Long iid = userSrv.getUser(p.getName()).getActualIteration();
        Iteration iteration = iterationSrv.getIteration(iid);
        List<Story> stories = storySrv.getStoriesByIterationId(iid);
        List<User> users = userSrv.readUsersByProductId(userSrv.getUser(
                p.getName()).getActualProduct());


        model.addAttribute("users", users);
        model.addAttribute("url_new", "story_list_save_story.html");
        model.addAttribute("url_del", "iteration_delStory.html");
        model.addAttribute("func", "func2a");
        model.addAttribute("active", "storyList");
        model.addAttribute("stories", stories);
        model.addAttribute("name", iteration.getName());
        model.addAttribute("storyModel", new Story());
        model.addAttribute("actionComment", "commentStoryList.html");

        session = utils.checkSessionAttr(session, userSrv.getUser(p.getName()),
                userSrv);
        return "story/backlog";
    }

    @RequestMapping("/story_list_save_story")
    public String story_list_save_story(final Principal p,
            @ModelAttribute("storyModel") final Story story,
            final BindingResult result, final Model model) {

        User u = userSrv.getUser(p.getName());
        story.setIdCreator(u.getId());

        storyValidator.validate(story, result);

        if (result.hasErrors()) {
            List<User> users = null;
            users = userSrv.readUsersByProductId(u.getActualProduct());

            model.addAttribute("saveAction", "story_list_save_story.html");
            model.addAttribute("users", users);
            model.addAttribute("assignee", story.getAssignee());
            model.addAttribute("storyModel", story);
            return "story/new_story";
        }

        storySrv.saveStory(story, u.getActualProduct(), u.getActualIteration());
        return "redirect:iteration_storyList.html";
    }

    @RequestMapping("/iteration_newStory")
    public String iterationNewStory(final Principal p, final Model model,
            HttpSession session) {
        model.addAttribute("story", new Story());
        model.addAttribute("url", "iteration_storySave.html");
        session = utils.checkSessionAttr(session, userSrv.getUser(p.getName()),
                userSrv);
        return "story/new_story";
    }

    @RequestMapping("/iteration_storySave")
    public String iterationStorySave(final Principal p,
            @ModelAttribute("story") final Story story) {
        User u = userSrv.getUser(p.getName());
        story.setIdCreator(u.getId());
        story.setStatusId(1L);
        storySrv.saveStory(story, u.getActualProduct(), u.getActualIteration());
        return "redirect:iteration_storyList.html";
    }

    @RequestMapping(value = "/storyList_upd", method = RequestMethod.POST)
    public String storyListUpd(
            @RequestParam(value = "item[]", required = false) final Long[] ids) {

        try {
            if (ids != null)
                iterationSrv.updatePriorityInIS(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "/storyList_board_upd", method = RequestMethod.POST)
    public String storyList_board_upd(final Principal p,
            @RequestParam(value = "item[]", required = false) final Long[] ids) {

        try {
            if (ids != null)
                storySrv.updatePrioritiesOfUnuserdStoriesFromBcklog(userSrv
                        .getUser(p.getName()).getActualProduct(), ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "/storyList_backlog_recieve", method = RequestMethod.POST)
    public String storyList_backlog_recieve(
            @RequestParam(value = "sid", required = true) final Long sid) {

        try {
            storySrv.deleteStoryFromIteration(sid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "/storyList_recieve", method = RequestMethod.POST)
    public String scrumBoardRecieve1(final Principal p,
            @RequestParam(value = "sid", required = true) final Long sid) {
        try {
            iterationSrv.addStoryToAnIteration(userSrv.getUser(p.getName())
                    .getActualIteration(), sid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Coldplay";
    }

    public static class IterationValidator implements Validator {

        @Override
        public boolean supports(final Class<?> clazz) {
            return Iteration.class.equals(clazz);
        }

        @Override
        public void validate(final Object obj, final Errors errors) {

            Iteration iteration = (Iteration) obj;
            if (iteration.getDateStart() != null
                    && iteration.getDateEnd() != null)
                if (iteration.getDateStart().after(iteration.getDateEnd())) {
                    errors.rejectValue("dateEnd", "iteration.end.error");
                }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                    "iteration.name.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateStart",
                    "iteration.dateStart.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateEnd",
                    "iteration.dateEnd.empty");
        }
    }

    public static class StoryValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return Story.class.equals(clazz);
        }

        @Override
        public void validate(Object obj, Errors errors) {
            Story story = (Story) obj;
            story.setStoryPoints(story.getStoryPoints().trim());
            story.setStoryPoints(story.getStoryPoints().toUpperCase());
            if (story.getStoryPoints().equals(""))
                story.setStoryPoints("?");

            try {
                if (story.getEstimate() < 0) {
                    errors.rejectValue("estimate", "story.estimate.negaive");
                }
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estimate",
                        "story.estimate.empty");
            } catch (NumberFormatException nfe) {
                errors.rejectValue("estimate", "story.estimate.nfe");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                    "story.name.empty");

            if (!errors.hasFieldErrors("storyPoints"))
                if (!(story.getStoryPoints().equals("XS")
                        || story.getStoryPoints().equals("S")
                        || story.getStoryPoints().equals("M")
                        || story.getStoryPoints().equals("L")
                        || story.getStoryPoints().equals("XL") || story
                        .getStoryPoints().equals("?"))) {
                    errors.rejectValue("storyPoints", "story.storyPoints.title");
                }

        }
    }
}
