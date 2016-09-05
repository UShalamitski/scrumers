package com.scrumers.api.dao;

import java.util.List;

import com.scrumers.model.Comment;
import com.scrumers.model.Story;

public interface StoryDao extends GenericDao<Long, Story> {

    Long selectId();

    void updateStatus(Long id, Long stid);

    void createWithId(Story s, Long pid);

    List<Story> readAll();

    Story readByTaskId(Long tid);

    List<Story> readByProductId(Long pid);

    List<Story> readUnusedStoriesFromBacklog(Long pid);

    List<Story> readByIterationId(Long iid);

    List<Long> readPriorities(Long iid, Long stat_id);

    List<Long> readPrioritiesOfUnusedStoriesFromBacklog(Long pid);

    void deleteFromIterationStory(Long id);

    void addTaskToAStory(Long sid, Long tid);

    void addUserToAStory(Long sid, Long uid);

    void comment(Long uid, Long sid, String comment);

    Comment findComment(Long cid);

    void deleteComment(Long cid, Long uid);

    List<Comment> readCommentsByStoryId(Long sid);
}
