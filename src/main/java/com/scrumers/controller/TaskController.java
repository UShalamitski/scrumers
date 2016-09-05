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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scrumers.model.Comment;
import com.scrumers.model.Story;
import com.scrumers.model.Task;
import com.scrumers.model.User;
import com.scrumers.api.service.StoryService;
import com.scrumers.api.service.TaskService;
import com.scrumers.api.service.UserService;

@Controller
public class TaskController {

    @Autowired
    private UserService userSrv;

    @Autowired
    private TaskService taskSrv;

    @Autowired
    private StoryService storySrv;

    private ControllerUtils utils = new ControllerUtils();

    private TaskValidator validator = new TaskValidator();

    @RequestMapping(value = "/commentTaskBoard")
    public String commentTaskBoard(
            @RequestParam(value = "tid", required = true) final Long tid,
            final Model model, Principal p) {
        Task task = taskSrv.getTask(tid);
        Long uid = userSrv.getUser(p.getName()).getId();
        Story story = storySrv.getStoryByTaskId(tid);
        List<Comment> comments = taskSrv.readCommentsByTaskId(tid);
        model.addAttribute("uid", uid);
        model.addAttribute("task", task);
        model.addAttribute("comments", comments);
        model.addAttribute("url_new", "addCommentTaskBoard.html");
        model.addAttribute("url_del", "delCommentTaskBoard.html");
        if (story != null)
            model.addAttribute("url_back", "task_board.html?sid=" + story.getId() + "&storyId="+ story.getStoryId());
        else
            model.addAttribute("url_back", "commentTaskBoard.html?tid=" + tid);
        return "comments/commentsTask";
    }

    @RequestMapping(value = "/addCommentTaskBoard", method = RequestMethod.POST)
    public String addCommentTaskBoard(
            @RequestParam(value = "tid", required = true) final Long tid,
            Principal p, @RequestParam(value = "comment") String comment) {
        taskSrv.commentTask(userSrv.getUser(p.getName()).getId(), tid, comment);
        return "redirect:commentTaskBoard.html?tid=" + tid;
    }

    @RequestMapping(value = "/delCommentTaskBoard", method = RequestMethod.GET)
    public String delCommentTaskBoard(
            @RequestParam(value = "cid", required = true) final Long cid,
            @RequestParam(value = "tid", required = true) final Long tid,
            Principal p) {
        taskSrv.deleteComment(cid);
        return "redirect:commentTaskBoard.html?tid=" + tid;
    }

    @RequestMapping("/task_board")
    public String taskBoard(
            @RequestParam(value = "sid", required = true) final Long sid,
            @RequestParam(value = "storyId", required = true) final Long storyId,
            final Principal p, final Model model, HttpSession session) {

        User u = userSrv.getUser(p.getName());
        List<User> users = userSrv.readUsersByProductId(u.getActualProduct());
        List<Task> tasks = taskSrv.getTasksByStoryId(sid);
        session = utils.checkSessionAttr(session, u, userSrv);

        model.addAttribute("users", users);
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskModel", new Task());
        model.addAttribute("sid", sid);
        model.addAttribute("storyId", storyId);
        model.addAttribute("actionComment", "commentTaskBoard.html");
        return "task/task_board";
    }

    @RequestMapping("/task_board_save")
    public String saveTask(final Principal p,
            @ModelAttribute("task") final Task task,
            final BindingResult result, final Model model,
            @RequestParam(value = "sid", required = true) final Long sid,
            @RequestParam(value = "storyId", required = true) final Long storyId) {
        User u = userSrv.getUser(p.getName());
        task.setIdCreator(u.getId());

        validator.validate(task, result);

        if (result.hasErrors()) {
            List<User> users = null;
            users = userSrv.readUsersByProductId(u.getActualProduct());
            model.addAttribute("users", users);
            model.addAttribute("assignee", task.getAssignee());
            model.addAttribute("task", task);
            model.addAttribute("storyId", storyId);
            model.addAttribute("sid", sid);
            return "task/new_task";
        }

        taskSrv.saveTask(task, sid);
        return "redirect:task_board.html?sid=" + sid + "&storyId=" + storyId;
    }

    @RequestMapping(value = "/task_delete", method = RequestMethod.POST)
    public String taskBoardDelete(
            @RequestParam(value = "id", required = false) final Long tid,
            @RequestParam(value = "id2", required = false) final Long sid,
            @RequestParam(value = "id3", required = false) final Long storyId) {
        taskSrv.deleteTask(tid);
        return "redirect:task_board.html?sid=" + sid + "&storyId=" + storyId;
    }

    @RequestMapping(value = "/task_board_upd1", method = RequestMethod.POST)
    public String tsdkBoardUpdate1(HttpSession session,
            @RequestParam(value = "item[]", required = true) final Long[] ids,
            @RequestParam(value = "sid", required = true) final Long sid) {
        taskSrv.updatePriorities(1l, sid, ids);
        return "";
    }

    @RequestMapping(value = "/task_board_upd2", method = RequestMethod.POST)
    public String tsdkBoardUpdate2(HttpSession session,
            @RequestParam(value = "item[]", required = true) final Long[] ids,
            @RequestParam(value = "sid", required = true) final Long sid) {
        taskSrv.updatePriorities(2l, sid, ids);
        return "";
    }

    @RequestMapping(value = "/task_board_upd3", method = RequestMethod.POST)
    public String tsdkBoardUpdate3(HttpSession session,
            @RequestParam(value = "item[]", required = true) final Long[] ids,
            @RequestParam(value = "sid", required = true) final Long sid) {
        taskSrv.updatePriorities(4l, sid, ids);
        return "";
    }

    @RequestMapping(value = "/task_board_recieve_1", method = RequestMethod.POST)
    public String taskBoardRecieve1(
            @RequestParam(value = "tid", required = true) final Long tid) {
        taskSrv.updateStatus(tid, 1L);
        return "";
    }

    @RequestMapping(value = "/task_board_recieve_2", method = RequestMethod.POST)
    public String taskBoardRecieve2(
            @RequestParam(value = "tid", required = true) final Long tid) {
        taskSrv.updateStatus(tid, 2L);
        return "";
    }

    @RequestMapping(value = "/task_board_recieve_3", method = RequestMethod.POST)
    public String taskBoardRecieve3(
            @RequestParam(value = "tid", required = true) final Long tid) {
        taskSrv.updateStatus(tid, 4L);
        return "";
    }

    public static class TaskValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return Task.class.equals(clazz);
        }

        @Override
        public void validate(Object obj, Errors errors) {
            Task task = (Task) obj;

            try {
                if (task.getEstimatePre() < 0) {
                    errors.rejectValue("estimatePre", "story.estimate.negaive");
                }
                ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                        "estimatePre", "story.estimate.empty");
            } catch (NumberFormatException nfe) {
                errors.rejectValue("estimatePre", "story.estimate.nfe");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "summary",
                    "story.name.empty");
        }
    }

}
