package com.scrumers.storage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.scrumers.api.dao.StoryDao;
import com.scrumers.model.Comment;
import com.scrumers.model.Story;
import com.scrumers.model.enums.StoryStatusEnum;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class StoryDaoImpl extends SqlSessionDaoSupport implements StoryDao {

    @Override
    public Long selectId() {
        return getSqlSession().selectOne("Story.selectId");
    }

    @Override
    public void createWithId(Story s, Long pid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", s.getId());
        map.put("storyId", s.getStoryId());
        map.put("name", s.getName());
        map.put("descr", s.getDescr());
        map.put("storyPoints", s.getStoryPoints());
        map.put("estimate", s.getEstimate());
        map.put("howToDemo", s.getHowToDemo());
        map.put("track", s.getTrack());
        map.put("status", s.getStatus());
        map.put("idCreator", s.getIdCreator());
        map.put("assignee", s.getAssignee());
        map.put("pid", pid);
        getSqlSession().insert("Story.createWithId", map);
    }

    @Override
    public void create(Story s) {
        getSqlSession().insert("Story.create", s);
    }

    @Override
    public Story read(Long id) {
        return getSqlSession().selectOne("Story.read", id);
    }

    @Override
    public List<Story> readAll() {
        return getSqlSession().selectList("Story.readAll");
    }

    @Override
    public Story readByTaskId(Long tid) {
        return getSqlSession().selectOne("Story.readByTaskId", tid);
    }

    @Override
    public List<Story> readByProductId(Long pid) {
        return getSqlSession().selectList("Story.readByProductId", pid);
    }

    @Override
    public List<Story> readByIterationId(Long iid) {
        return getSqlSession().selectList("Story.readByIterationId", iid);
    }

    @Override
    public void update(Story s) {
        getSqlSession().update("Story.update", s);
    }

    @Override
    public void delete(final Long id) {
        getSqlSession().delete("Story.delete", id);
    }

    @Override
    public void deleteFromIterationStory(Long id) {
        getSqlSession().delete("Story.deleteFromIterationStory", id);
    }

    @Override
    public void addTaskToAStory(Long sid, Long tid) {
        getSqlSession().insert("Story.addTaskToAStory", ImmutableMap.of("sid", sid, "tid", tid));
    }

    @Override
    public void addUserToAStory(Long sid, Long uid) {
        getSqlSession().insert("Story.addUserToAStory", ImmutableMap.of("sid", sid, "uid", uid));
    }

    @Override
    public void updateStatus(Long id, StoryStatusEnum status) {
        getSqlSession().update("Story.updateStatus", ImmutableMap.of("id", id, "status", status));
    }

    @Override
    public List<Long> readPriorities(Long iid, StoryStatusEnum status) {
        return getSqlSession().selectList("Story.readPriorities", ImmutableMap.of("iid", iid, "status", status));
    }

    @Override
    public List<Story> readUnusedStoriesFromBacklog(Long pid) {
        return getSqlSession().selectList("Story.readUnusedStoriesFromBacklog", pid);
    }

    @Override
    public List<Long> readPrioritiesOfUnusedStoriesFromBacklog(Long pid) {
        return getSqlSession().selectList("Story.readPrioritiesOfUnusedStoriesFromBacklog", pid);
    }

    @Override
    public void comment(Long uid, Long sid, String comment) {
        getSqlSession().insert("Story.commentStory", ImmutableMap.of("uid", uid, "sid", sid, "comment", comment));
    }

    @Override
    public Comment findComment(Long cid) {
        return getSqlSession().selectOne("Story.findCommentById", cid);
    }

    @Override
    public void deleteComment(Long cid, Long uid) {
        getSqlSession().insert("Story.deleteComment", ImmutableMap.of("cid", cid, "uid", uid));
    }

    @Override
    public List<Comment> readCommentsByStoryId(Long sid) {
        return getSqlSession().selectList("Story.readCommentsByStoryId", sid);
    }

}
