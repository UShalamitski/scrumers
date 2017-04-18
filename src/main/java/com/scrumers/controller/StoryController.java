package com.scrumers.controller;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.scrumers.api.service.ProductService;
import com.scrumers.api.service.StoryService;
import com.scrumers.api.service.UserService;
import com.scrumers.model.Comment;
import com.scrumers.model.Product;
import com.scrumers.model.Story;
import com.scrumers.model.User;
import com.scrumers.model.enums.StoryStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoryController {

    @Autowired
    private UserService userSrv;

    @Autowired
    private StoryService storySrv;

    @Autowired
    private ProductService prodSrv;

    private StoryValidator validator = new StoryValidator();

    private ControllerUtils utils = new ControllerUtils();

    @RequestMapping("/story_backlog")
    public String Backlog(final Principal p, final Model model,
                          HttpSession session) {

        List<Story> stories;
        Product product = prodSrv.getProduct(userSrv.getUser(p.getName()).getActualProduct());
        stories = storySrv.getStoriesByProductId(userSrv.getUser(p.getName())
                .getActualProduct());
        List<User> users = userSrv.readUsersByProductId(userSrv.getUser(
                p.getName()).getActualProduct());


        model.addAttribute("url_new", "story_backlog_save.html");
        model.addAttribute("url_del", "story_backlog_delete.html");
        model.addAttribute("stories", stories);
        model.addAttribute("func", "func2");
        model.addAttribute("active", "backlog");
        model.addAttribute("users", users);
        model.addAttribute("storyModel", new Story());
        model.addAttribute("name", product.getName());
        model.addAttribute("actionComment", "commentStoryBacklog.html");

        session = utils.checkSessionAttr(session, userSrv.getUser(p.getName()),
                userSrv);
        return "story/backlog";
    }

    @RequestMapping(value = "/commentStoryIterationPlaning")
    public String commentStoryIterationPlaning(
            @RequestParam(value = "sid", required = true) final Long sid,
            final Model model, Principal p) {
        Story story = storySrv.getStory(sid);
        Long uid = userSrv.getUser(p.getName()).getId();
        List<Comment> comments = storySrv.readCommentsByStoryId(sid);
        model.addAttribute("uid", uid);
        model.addAttribute("story", story);
        model.addAttribute("comments", comments);
        model.addAttribute("url_new", "addCommentStoryIterationPlaning.html");
        model.addAttribute("url_del", "delCommentIterationPlaning.html");
        model.addAttribute("url_back", "iteration_planing.html");
        return "comments/comments";
    }

    @RequestMapping(value = "/addCommentStoryIterationPlaning", method = RequestMethod.POST)
    public String addCommentStoryIterationPlanning(
            @RequestParam(value = "sid", required = true) final Long sid,
            Principal p, @RequestParam(value = "comment") String comment) {
        comment(sid, p, comment);
        return "redirect:commentStoryIterationPlaning.html?sid=" + sid;
    }

    @RequestMapping(value = "/delCommentIterationPlaning", method = RequestMethod.GET)
    public String delCommentIterationPlaning(
            @RequestParam(value = "cid", required = true) final Long cid,
            @RequestParam(value = "sid", required = true) final Long sid,
            Principal p) {
        storySrv.deleteComment(cid, userSrv.getUser(p.getName()).getId());
        return "redirect:commentStoryIterationPlaning.html?sid=" + sid;
    }

    @RequestMapping(value = "/commentStoryScrumBoard")
    public String commentStoryScrumBoard1(
            @RequestParam(value = "sid", required = true) final Long sid,
            final Model model, Principal p) {
        Story story = storySrv.getStory(sid);
        List<Comment> comments = storySrv.readCommentsByStoryId(sid);
        Long uid = userSrv.getUser(p.getName()).getId();

        model.addAttribute("uid", uid);
        model.addAttribute("story", story);
        model.addAttribute("comments", comments);
        model.addAttribute("url_new", "addCommentStoryScrumBoard.html");
        model.addAttribute("url_del", "delCommentStoryScrumBoard.html");
        model.addAttribute("url_back", "scrum_board.html");
        return "comments/comments";
    }

    @RequestMapping(value = "/addCommentStoryScrumBoard", method = RequestMethod.POST)
    public String addCommentStoryScrumBoard(
            @RequestParam(value = "sid", required = true) final Long sid,
            Principal p, @RequestParam(value = "comment") String comment) {
        comment(sid, p, comment);
        return "redirect:commentStoryScrumBoard.html?sid=" + sid;
    }

    @RequestMapping(value = "/delCommentStoryScrumBoard", method = RequestMethod.GET)
    public String delCommentStoryScrumBoard(
            @RequestParam(value = "cid", required = true) final Long cid,
            @RequestParam(value = "sid", required = true) final Long sid,
            Principal p) {
        storySrv.deleteComment(cid, userSrv.getUser(p.getName()).getId());
        return "redirect:commentStoryScrumBoard.html?sid=" + sid;
    }

    @RequestMapping(value = "/commentStoryList")
    public String commentStoryList(
            @RequestParam(value = "sid", required = true) final Long sid,
            final Model model, Principal p) {
        Story story = storySrv.getStory(sid);
        Long uid = userSrv.getUser(p.getName()).getId();
        List<Comment> comments = storySrv.readCommentsByStoryId(sid);
        model.addAttribute("uid", uid);
        model.addAttribute("story", story);
        model.addAttribute("comments", comments);
        model.addAttribute("url_new", "addCommentStoryList.html");
        model.addAttribute("url_del", "delCommentStoryList.html");
        model.addAttribute("url_back", "iteration_storyList.html");
        return "comments/comments";
    }

    @RequestMapping(value = "/addCommentStoryList", method = RequestMethod.POST)
    public String addCommentStoryList(
            @RequestParam(value = "sid", required = true) final Long sid,
            Principal p, @RequestParam(value = "comment") String comment) {
        comment(sid, p, comment);
        return "redirect:commentStoryList.html?sid=" + sid;
    }

    @RequestMapping(value = "/delCommentStoryList", method = RequestMethod.GET)
    public String delCommentStoryList(
            @RequestParam(value = "cid", required = true) final Long cid,
            @RequestParam(value = "sid", required = true) final Long sid,
            Principal p) {
        storySrv.deleteComment(cid, userSrv.getUser(p.getName()).getId());
        return "redirect:commentStoryList.html?sid=" + sid;
    }

    @RequestMapping(value = "/commentStoryBacklog")
    public String commentStoryBacklog(
            @RequestParam(value = "sid", required = true) final Long sid,
            final Model model, Principal p) {
        Story story = storySrv.getStory(sid);
        Long uid = userSrv.getUser(p.getName()).getId();
        List<Comment> comments = storySrv.readCommentsByStoryId(sid);
        model.addAttribute("uid", uid);
        model.addAttribute("story", story);
        model.addAttribute("comments", comments);
        model.addAttribute("url_new", "addCommentStoryBacklog.html");
        model.addAttribute("url_del", "delCommentStoryBacklog.html");
        model.addAttribute("url_back", "story_backlog.html");
        return "comments/comments";
    }

    @RequestMapping(value = "/addCommentStoryBacklog", method = RequestMethod.POST)
    public String addCommentStoryBacklog(
            @RequestParam(value = "sid", required = true) final Long sid,
            Principal p, @RequestParam(value = "comment") String comment) {
        comment(sid, p, comment);
        return "redirect:commentStoryBacklog.html?sid=" + sid;
    }

    @RequestMapping(value = "/delCommentStoryBacklog", method = RequestMethod.GET)
    public String delCommentStoryBacklog(
            @RequestParam(value = "cid", required = true) final Long cid,
            @RequestParam(value = "sid", required = true) final Long sid,
            Principal p) {
        storySrv.deleteComment(cid, userSrv.getUser(p.getName()).getId());
        return "redirect:commentStoryBacklog.html?sid=" + sid;
    }

    @RequestMapping(value = "/commentStoryIteration")
    public String commentStoryIteration(
            @RequestParam(value = "sid", required = true) final Long sid,
            final Model model, Principal p) {
        Story story = storySrv.getStory(sid);
        Long uid = userSrv.getUser(p.getName()).getId();
        List<Comment> comments = storySrv.readCommentsByStoryId(sid);
        model.addAttribute("uid", uid);
        model.addAttribute("story", story);
        model.addAttribute("comments", comments);
        model.addAttribute("url_new", "addCommentStoryIteration.html");
        model.addAttribute("url_del", "delCommentStoryIteration.html");
        model.addAttribute("url_back", "iterations.html");
        return "comments/comments";
    }

    @RequestMapping(value = "/addCommentStoryIteration", method = RequestMethod.POST)
    public String addCommentStoryIteration(
            @RequestParam(value = "sid", required = true) final Long sid,
            Principal p, @RequestParam(value = "comment") String comment) {
        comment(sid, p, comment);
        return "redirect:commentStoryIteration.html?sid=" + sid;
    }

    @RequestMapping(value = "/delCommentStoryIteration", method = RequestMethod.GET)
    public String delCommentStoryIteration(
            @RequestParam(value = "cid", required = true) final Long cid,
            @RequestParam(value = "sid", required = true) final Long sid,
            Principal p) {
        storySrv.deleteComment(cid, userSrv.getUser(p.getName()).getId());
        return "redirect:commentStoryIteration.html?sid=" + sid;
    }

    @RequestMapping("/story_new")
    public String newStory(final Principal p, final Model model,
                           HttpSession session) {
        model.addAttribute("story", new Story());
        model.addAttribute("url", "story_backlog_save.html");
        session = utils.checkSessionAttr(session, userSrv.getUser(p.getName()),
                userSrv);
        return "story/new_story";
    }

    @RequestMapping(value = "/story_backlog_delete")
    public String deleteProduct(
            @RequestParam(value = "id", required = false) final Long id) {
        storySrv.deleteStory(id);
        return "redirect:story_backlog.html";
    }

    @RequestMapping("/story_backlog_save")
    public String story_backlog_save(final Principal p,
                                     @ModelAttribute("storyModel") final Story story,
                                     final BindingResult result, final Model model) {
        User u = userSrv.getUser(p.getName());
        story.setIdCreator(u.getId());

        validator.validate(story, result);

        if (result.hasErrors()) {
            List<User> users = null;
            users = userSrv.readUsersByProductId(u.getActualProduct());
            model.addAttribute("users", users);
            model.addAttribute("assignee", story.getAssignee());
            model.addAttribute("saveAaction", "story_backlog_save.html");
            model.addAttribute("storyModel", story);
            return "story/new_story";
        }

        storySrv.saveStory(story, u.getActualProduct(), null);
        return "redirect:story_backlog.html";
    }

    @RequestMapping("/scrum_board")
    public String scrumBoard(final Principal p, final Model model,
                             HttpSession session) {

        User u = userSrv.getUser(p.getName());
        List<User> users = userSrv.readUsersByProductId(u.getActualProduct());
        List<Story> stories = storySrv.getStoriesByIterationId(u
                .getActualIteration());
        session = utils.checkSessionAttr(session, u, userSrv);

        model.addAttribute("stories", stories);
        model.addAttribute("users", users);
        model.addAttribute("storyModel", new Story());
        model.addAttribute("actionComment", "commentStoryScrumBoard.html");
        return "story/scrum_board";
    }

    @RequestMapping("/story_scrum_board_save")
    public String storyScrumBoardSave(final Principal p, final Model model,
                                      @ModelAttribute("storyModel") final Story story,
                                      final BindingResult result) {

        User u = userSrv.getUser(p.getName());
        story.setIdCreator(u.getId());

        validator.validate(story, result);

        if (result.hasErrors()) {
            List<User> users = null;
            users = userSrv.readUsersByProductId(u.getActualProduct());
            model.addAttribute("users", users);
            model.addAttribute("saveAction", "story_scrum_board_save.html");
            model.addAttribute("storyModel", story);
            return "story/new_story";
        }

        storySrv.saveStory(story, u.getActualProduct(), u.getActualIteration());
        return "redirect:scrum_board.html";
    }

    @RequestMapping(value = "/board_del", method = RequestMethod.POST)
    public String boardDel(Principal p,
                           @RequestParam(value = "id", required = true) final Long sid,
                           HttpSession session) {

        storySrv.deleteStoryFromIteration(sid);
        return "redirect:scrum_board.html";
    }

    @RequestMapping(value = "/backlog_del", method = RequestMethod.POST)
    public String backlogDel(
            @RequestParam(value = "id", required = false) final Long sid) {
        storySrv.deleteStory(sid);
        return "redirect:story_backlog.html";
    }

    @RequestMapping(value = "/backlog_upd", method = RequestMethod.POST)
    public String backlog_upd(
            @RequestParam(value = "item[]", required = false) final Long[] ids) {

        try {
            prodSrv.updatePriorityInPS(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:story_backlog.html";
    }

    @RequestMapping(value = "/story/update/priorities/status/{status}", method = RequestMethod.POST)
    public void updatePriorities(HttpSession session, @PathVariable("status") StoryStatusEnum status,
                                 @RequestParam(value = "item[]") final Long[] ids, HttpServletResponse resp) {
        storySrv.updatePriorities(StoryStatusEnum.TODO, (Long) session.getAttribute("iter_id"), ids);
        resp.setStatus(HttpStatus.OK.value());
    }

    @RequestMapping(value = "/story/{storyId}/update/status/{status}", method = RequestMethod.POST)
    public void updateStatus(Principal p, @PathVariable(value = "storyId") final Long storyId,
                             @PathVariable(value = "status") final StoryStatusEnum status, HttpServletResponse resp) {
        long iterationId = userSrv.getUser(p.getName()).getActualIteration();
            storySrv.updateStatus(storyId, iterationId, status);
        resp.setStatus(HttpStatus.OK.value());
    }

    public void comment(final Long sid, Principal p, String comment) {
        if (sid != null && !comment.isEmpty()) {
            Long uid = ((User) userSrv.getUser(p.getName())).getId();
            storySrv.comment(uid, sid, comment);
        }
    }

//    public void delComment(final Long cid, final String login, Principal p) {
//        Long uid = userSrv.getUser(p.getName()).getId();
//        Long uidComment = userSrv.getUser(login).getId();
//        List<Role> roles = userSrv.getUsersRoles(uid);
//        boolean flagAdmin = false;
//        for (Role role : roles) {
//            if (role.getId() == 1) {
//                flagAdmin = true;
//                break;
//            }
//        }
//
//        Comment comment = storySrv.getComment(cid);
//
//        if (cid != null && comment.getLogin().equals(p.getName())) {
//            storySrv.deleteComment(cid, uidComment);
//        } else {
//            if (flagAdmin) {
//                storySrv.deleteComment(cid, uidComment);
//            }
//        }
//    }

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
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estimate", "story.estimate.empty");
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
