package com.scrumers.api.dao;

import java.util.List;

import com.scrumers.model.Comment;
import com.scrumers.model.Task;
import com.scrumers.model.enums.StoryStatusEnum;

public interface TaskDao extends GenericDao<Long, Task> {

    List<Task> readAll();

    Long selectId();
    
    void commentTask(Long uid, Long tid, String comment);

    void createWithId(Task s, Long sid);

    void updateStatus(Long id, StoryStatusEnum status);

    void updatePriorityInST(Long tid, Long priority);

    List<Task> readByStoryId(Long sid);

    void deleteComment(Long cid);

    List<Long> readPriorities(Long sid, StoryStatusEnum status);

    List<Comment> readCommentsByTaskId(Long tid);
}
