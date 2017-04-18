package com.scrumers.api.service;

import java.util.List;

import com.scrumers.model.Comment;
import com.scrumers.model.Story;
import com.scrumers.model.enums.StoryStatusEnum;

public interface StoryService {

    Story getStory(Long id);

    void updateStatus(Long id, StoryStatusEnum status);

    void updateStatus(Long sid, Long iid, StoryStatusEnum status);

    void updateStatusToDone(Long sid, Long iid, StoryStatusEnum status);

    boolean IterationStoryIsDone(Long iid, Long sid);

    void saveStory(Story s, Long pid, Long iid);

    void deleteStory(Long[] id);

    void deleteStory(Long id);

    void deleteStory(Long sid, Long iid);

    void deleteStoryFromIteration(Long id);

    void updatePriorities(StoryStatusEnum status, Long iid, Long[] ids);

    void updatePrioritiesOfUnuserdStoriesFromBcklog(Long pid, Long[] ids);

    List<Story> getStories();

    List<Story> getUnusedStoriesFromBacklog(Long pid);

    Story getStoryByTaskId(Long tid);

    List<Story> getStoriesByProductId(Long pid);

    List<Story> getStoriesByIterationId(Long iid);

    void addTaskToAStory(Long sid, Long tid);

    void addUserToAStory(Long sid, Long uid);

    void comment(Long uid, Long sid, String comment);

    Comment getComment(Long cid);

    void deleteComment(Long cid, Long uid);

    List<Comment> readCommentsByStoryId(Long sid);

}