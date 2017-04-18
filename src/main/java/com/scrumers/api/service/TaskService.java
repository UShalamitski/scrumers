package com.scrumers.api.service;

import java.util.List;

import com.scrumers.model.Comment;
import com.scrumers.model.Task;
import com.scrumers.model.enums.StoryStatusEnum;

public interface TaskService {

    Task getTask(Long id);

    void saveTask(Task t, Long sid);

    void updateStatus(Long id, StoryStatusEnum status);

    void updatePriorities(StoryStatusEnum status, Long sid, Long[] ids);

    void saveTask(Task t);

    void deleteTask(Long[] id);

    void deleteTask(Long id);

    List<Task> getTasks();

    List<Task> getTasksByStoryId(Long sid);

    void commentTask(Long uid, Long tid, String comment);

    void deleteComment(Long cid);

    List<Comment> readCommentsByTaskId(Long tid);

}