package com.scrumers.storage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.scrumers.api.dao.TaskDao;
import com.scrumers.model.Comment;
import com.scrumers.model.Task;
import com.scrumers.model.enums.StoryStatusEnum;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TaskDaoImpl extends SqlSessionDaoSupport implements TaskDao {

    @Override
    public void create(Task t) {
        getSqlSession().insert("Task.create", t);
    }

    @Override
    public Long selectId() {
        return getSqlSession().selectOne("Task.selectId");
    }

    @Override
    public void createWithId(Task t, Long sid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", t.getId());
        map.put("taskId", t.getTaskId());
        map.put("summary", t.getSummary());
        map.put("estimatePre", t.getEstimatePre());
        map.put("estimateReal", t.getEstimateReal());
        map.put("assignee", t.getAssignee());
        map.put("status", t.getStatus());
        map.put("idCreator", t.getIdCreator());
        map.put("sid", sid);
        getSqlSession().insert("Task.createWithId", map);
    }

    @Override
    public Task read(Long id) {
        return getSqlSession().selectOne("Task.read", id);
    }

    @Override
    public List<Task> readAll() {
        return getSqlSession().selectList("Task.readAll");
    }

    @Override
    public List<Task> readByStoryId(Long sid) {
        return getSqlSession().selectList("Task.readByStoryId", sid);
    }

    @Override
    public List<Long> readPriorities(Long sid, StoryStatusEnum status) {
        return getSqlSession().selectList("Task.readPriorities", ImmutableMap.of("sid", sid, "status", status));
    }

    @Override
    public void update(Task t) {
        getSqlSession().update("Task.update", t);
    }

    @Override
    public void updateStatus(Long id, StoryStatusEnum status) {
        getSqlSession().update("Task.updateStatus", ImmutableMap.of("id", id, "status", status));
    }

    @Override
    public void updatePriorityInST(Long tid, Long priority) {
        getSqlSession().update("Task.updatePriorityInST", ImmutableMap.of("priority", priority, "tid", tid));
    }

    @Override
    public void delete(final Long id) {
        getSqlSession().delete("Task.delete", id);
    }

    @Override
    public void deleteComment(final Long cid) {
        getSqlSession().delete("Task.deleteComment", cid);
    }

    @Override
    public List<Comment> readCommentsByTaskId(Long tid) {
        return getSqlSession().selectList("Task.readCommentsByTaskId", tid);
    }

    @Override
    public void commentTask(Long uid, Long tid, String comment) {
        getSqlSession().insert("Task.commentTask", ImmutableMap.of("uid", uid, "tid", tid, "comment", comment));
    }

}
