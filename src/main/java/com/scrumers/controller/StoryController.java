package com.scrumers.controller;

import com.scrumers.api.service.ProductService;
import com.scrumers.api.service.StoryService;
import com.scrumers.api.service.UserService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class StoryController {

    @Autowired
    private UserService userService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private ProductService productService;

    private StoryValidator validator = new StoryValidator();
    private ControllerUtils utils = new ControllerUtils();

    @RequestMapping("/product/backlog")
    public String backlog(final Principal principal, final Model model, HttpSession session) {
        User user = userService.getUser(principal.getName());

        model.addAttribute("url_new", "story_backlog_save.html");
        model.addAttribute("url_del", "product/backlog/delete.html");
        model.addAttribute("stories", storyService.getStoriesByProductId(user.getActualProduct()));
        model.addAttribute("func", "func2");
        model.addAttribute("active", "backlog");
        model.addAttribute("users", userService.readUsersByProductId(user.getActualProduct()));
        model.addAttribute("storyModel", new Story());
        model.addAttribute("name", productService.getProduct(user.getActualProduct()).getName());
        model.addAttribute("actionComment", "commentStoryBacklog.html");

        session = utils.checkSessionAttr(session, userService.getUser(principal.getName()), userService);
        return "story/backlog";
    }

    @RequestMapping("/product/board")
    public String scrumBoard(final Principal principal, final Model model, HttpSession session) {
        User user = userService.getUser(principal.getName());
        utils.checkSessionAttr(session, user, userService);
        model.addAttribute("stories", storyService.getStoriesByIterationId(user.getActualIteration()));
        model.addAttribute("users", userService.readUsersByProductId(user.getActualProduct()));
        model.addAttribute("storyModel", new Story());
        model.addAttribute("actionComment", "commentStoryScrumBoard.html");
        return "story/product/board";
    }

    @RequestMapping(value = "/commentStoryIterationPlaning")
    public String commentStoryIterationPlaning(@RequestParam("sid") final Long storyId, final Model model,
                                               Principal principal) {
        model.addAttribute("uid", userService.getUser(principal.getName()).getId());
        model.addAttribute("story", storyService.getStory(storyId));
        model.addAttribute("comments", storyService.readCommentsByStoryId(storyId));
        model.addAttribute("url_new", "addCommentStoryIterationPlaning.html");
        model.addAttribute("url_del", "delCommentIterationPlaning.html");
        model.addAttribute("url_back", "iteration_planing.html");
        return "comments/comments";
    }

    @RequestMapping(value = "/addCommentStoryIterationPlaning", method = RequestMethod.POST)
    public String addCommentStoryIterationPlanning(@RequestParam("sid") final Long storyId, Principal principal,
                                                   @RequestParam("comment") String comment) {
        comment(storyId, principal, comment);
        return "redirect:commentStoryIterationPlaning.html?sid=" + storyId;
    }

    @RequestMapping(value = "/delCommentIterationPlaning", method = RequestMethod.GET)
    public String delCommentIterationPlaning(@RequestParam("cid") final Long commentId,
                                             @RequestParam("sid") final Long storyId, Principal principal) {
        storyService.deleteComment(commentId, userService.getUser(principal.getName()).getId());
        return "redirect:commentStoryIterationPlaning.html?sid=" + storyId;
    }

    @RequestMapping(value = "/commentStoryScrumBoard")
    public String commentStoryScrumBoard1(@RequestParam("sid") final Long storyId, final Model model,
                                          Principal principal) {
        model.addAttribute("uid", userService.getUser(principal.getName()).getId());
        model.addAttribute("story", storyService.getStory(storyId));
        model.addAttribute("comments", storyService.readCommentsByStoryId(storyId));
        model.addAttribute("url_new", "addCommentStoryScrumBoard.html");
        model.addAttribute("url_del", "delCommentStoryScrumBoard.html");
        model.addAttribute("url_back", "product/board.html");
        return "comments/comments";
    }

    @RequestMapping(value = "/addCommentStoryScrumBoard", method = RequestMethod.POST)
    public String addCommentStoryScrumBoard(@RequestParam("sid") final Long storyid,
                                            @RequestParam("comment") String comment, Principal principal) {
        comment(storyid, principal, comment);
        return "redirect:commentStoryScrumBoard.html?sid=" + storyid;
    }

    @RequestMapping(value = "/delCommentStoryScrumBoard", method = RequestMethod.GET)
    public String delCommentStoryScrumBoard(@RequestParam("cid") final Long commentId,
                                            @RequestParam("sid") final Long storyId, Principal principal) {
        storyService.deleteComment(commentId, userService.getUser(principal.getName()).getId());
        return "redirect:commentStoryScrumBoard.html?sid=" + storyId;
    }

    @RequestMapping(value = "/commentStoryList")
    public String commentStoryList(@RequestParam("sid") final Long storyId, final Model model, Principal principal) {
        model.addAttribute("uid", userService.getUser(principal.getName()).getId());
        model.addAttribute("story", storyService.getStory(storyId));
        model.addAttribute("comments", storyService.readCommentsByStoryId(storyId));
        model.addAttribute("url_new", "addCommentStoryList.html");
        model.addAttribute("url_del", "delCommentStoryList.html");
        model.addAttribute("url_back", "iteration_storyList.html");
        return "comments/comments";
    }

    @RequestMapping(value = "/addCommentStoryList", method = RequestMethod.POST)
    public String addCommentStoryList(@RequestParam("sid") final Long storyId, Principal principal,
                                      @RequestParam("comment") String comment) {
        comment(storyId, principal, comment);
        return "redirect:commentStoryList.html?sid=" + storyId;
    }

    @RequestMapping(value = "/delCommentStoryList", method = RequestMethod.GET)
    public String delCommentStoryList(@RequestParam("cid") final Long commentId,
                                      @RequestParam("sid") final Long storyId, Principal principal) {
        storyService.deleteComment(commentId, userService.getUser(principal.getName()).getId());
        return "redirect:commentStoryList.html?sid=" + storyId;
    }

    @RequestMapping(value = "/commentStoryBacklog")
    public String commentStoryBacklog(@RequestParam("sid") final Long storyId, final Model model, Principal principal) {
        model.addAttribute("uid", userService.getUser(principal.getName()).getId());
        model.addAttribute("story", storyService.getStory(storyId));
        model.addAttribute("comments", storyService.readCommentsByStoryId(storyId));
        model.addAttribute("url_new", "addCommentStoryBacklog.html");
        model.addAttribute("url_del", "delCommentStoryBacklog.html");
        model.addAttribute("url_back", "product/backlog.html");
        return "comments/comments";
    }

    @RequestMapping(value = "/addCommentStoryBacklog", method = RequestMethod.POST)
    public String addCommentStoryBacklog(@RequestParam("sid") final Long storyId, Principal principal,
                                         @RequestParam("comment") String comment) {
        comment(storyId, principal, comment);
        return "redirect:commentStoryBacklog.html?sid=" + storyId;
    }

    @RequestMapping(value = "/delCommentStoryBacklog", method = RequestMethod.GET)
    public String delCommentStoryBacklog(@RequestParam("cid") final Long commentId,
                                         @RequestParam("sid") final Long storyId, Principal p) {
        storyService.deleteComment(commentId, userService.getUser(p.getName()).getId());
        return "redirect:commentStoryBacklog.html?sid=" + storyId;
    }

    @RequestMapping(value = "/commentStoryIteration")
    public String commentStoryIteration(@RequestParam("sid") final Long storyId, final Model model,
                                        Principal principal) {
        model.addAttribute("uid", userService.getUser(principal.getName()).getId());
        model.addAttribute("story", storyService.getStory(storyId));
        model.addAttribute("comments", storyService.readCommentsByStoryId(storyId));
        model.addAttribute("url_new", "addCommentStoryIteration.html");
        model.addAttribute("url_del", "delCommentStoryIteration.html");
        model.addAttribute("url_back", "iterations.html");
        return "comments/comments";
    }

    @RequestMapping(value = "/addCommentStoryIteration", method = RequestMethod.POST)
    public String addCommentStoryIteration(@RequestParam("sid") final Long storyId, Principal principal,
                                           @RequestParam("comment") String comment) {
        comment(storyId, principal, comment);
        return "redirect:commentStoryIteration.html?sid=" + storyId;
    }

    @RequestMapping(value = "/delCommentStoryIteration", method = RequestMethod.GET)
    public String delCommentStoryIteration(@RequestParam("cid") final Long commentId,
                                           @RequestParam("sid") final Long storyId, Principal principal) {
        storyService.deleteComment(commentId, userService.getUser(principal.getName()).getId());
        return "redirect:commentStoryIteration.html?sid=" + storyId;
    }

    @RequestMapping("/story_new")
    public String newStory(final Principal principal, final Model model, HttpSession session) {
        model.addAttribute("story", new Story());
        model.addAttribute("url", "story_backlog_save.html");
        utils.checkSessionAttr(session, userService.getUser(principal.getName()), userService);
        return "story/new_story";
    }

    @RequestMapping(value = "/product/backlog/delete")
    public String deleteProduct(@RequestParam("id") final Long storyId) {
        storyService.deleteStory(storyId);
        return "redirect:product/backlog.html";
    }

    @RequestMapping("/story_backlog_save")
    public String story_backlog_save(final Principal principal, @ModelAttribute("storyModel") final Story story,
                                     final BindingResult result, final Model model) {
        User user = userService.getUser(principal.getName());
        story.setIdCreator(user.getId());

        validator.validate(story, result);
        if (result.hasErrors()) {
            List<User> users = userService.readUsersByProductId(user.getActualProduct());
            model.addAttribute("users", users);
            model.addAttribute("assignee", story.getAssignee());
            model.addAttribute("saveAaction", "story_backlog_save.html");
            model.addAttribute("storyModel", story);
            return "story/new_story";
        }

        storyService.saveStory(story, user.getActualProduct(), null);
        return "redirect:product/backlog.html";
    }

    @RequestMapping("/story_scrum_board_save")
    public String storyScrumBoardSave(final Principal principal, final Model model,
                                      @ModelAttribute("storyModel") final Story story, final BindingResult result) {
        User user = userService.getUser(principal.getName());
        story.setIdCreator(user.getId());

        validator.validate(story, result);
        if (result.hasErrors()) {
            model.addAttribute("users", userService.readUsersByProductId(user.getActualProduct()));
            model.addAttribute("saveAction", "story_scrum_board_save.html");
            model.addAttribute("storyModel", story);
            return "story/new_story";
        }

        storyService.saveStory(story, user.getActualProduct(), user.getActualIteration());
        return "redirect:product/board.html";
    }

    @RequestMapping(value = "/board_del", method = RequestMethod.POST)
    public String boardDel(@RequestParam("id") final Long storyId) {
        storyService.deleteStoryFromIteration(storyId);
        return "redirect:product/board.html";
    }

    @RequestMapping(value = "/backlog_del", method = RequestMethod.POST)
    public String backlogDel(@RequestParam("id") final Long storyId) {
        storyService.deleteStory(storyId);
        return "redirect:product/backlog.html";
    }

    @RequestMapping(value = "/backlog_upd", method = RequestMethod.POST)
    public String backlog_upd(@RequestParam("item[]") final Long[] ids) {
        productService.updatePriorityInPS(ids);
        return "redirect:product/backlog.html";
    }

    @RequestMapping(value = "/story/update/priorities/status/{status}", method = RequestMethod.POST)
    public void updatePriorities(HttpSession session, @PathVariable("status") StoryStatusEnum status,
                                 @RequestParam(value = "item[]") final Long[] ids, HttpServletResponse resp) {
        storyService.updatePriorities(StoryStatusEnum.TODO, (Long) session.getAttribute("iter_id"), ids);
        resp.setStatus(HttpStatus.OK.value());
    }

    @RequestMapping(value = "/story/{storyId}/update/status/{status}", method = RequestMethod.POST)
    public void updateStatus(Principal p, @PathVariable(value = "storyId") final Long storyId,
                             @PathVariable(value = "status") final StoryStatusEnum status, HttpServletResponse resp) {
        long iterationId = userService.getUser(p.getName()).getActualIteration();
        storyService.updateStatus(storyId, iterationId, status);
        resp.setStatus(HttpStatus.OK.value());
    }

    public void comment(final Long storyId, Principal principal, String comment) {
        if (storyId != null && !comment.isEmpty()) {
            Long userId = userService.getUser(principal.getName()).getId();
            storyService.comment(userId, storyId, comment);
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
            if (story.getStoryPoints().equals("")) {
                story.setStoryPoints("?");
            }

            try {
                if (story.getEstimate() < 0) {
                    errors.rejectValue("estimate", "story.estimate.negaive");
                }
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estimate", "story.estimate.empty");
            } catch (NumberFormatException nfe) {
                errors.rejectValue("estimate", "story.estimate.nfe");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "story.name.empty");

            if (!errors.hasFieldErrors("storyPoints"))
                if (!(story.getStoryPoints().equals("XS")
                        || story.getStoryPoints().equals("S")
                        || story.getStoryPoints().equals("M")
                        || story.getStoryPoints().equals("L")
                        || story.getStoryPoints().equals("XL")
                        || story.getStoryPoints().equals("?"))) {
                    errors.rejectValue("storyPoints", "story.storyPoints.title");
                }
        }
    }
}
