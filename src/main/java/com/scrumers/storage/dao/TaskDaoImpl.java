package com.scrumers.storage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.scrumers.api.dao.TaskDao;
import com.scrumers.model.Comment;
import com.scrumers.model.Task;

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
        map.put("statusId", t.getStatusId());
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
    public List<Long> readPriorities(Long sid, Long stat_id) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("sid", sid);
        map.put("stat_id", stat_id);
        return getSqlSession().selectList("Task.readPriorities", map);
    }

    @Override
    public void update(Task t) {
        getSqlSession().update("Task.update", t);
    }

    @Override
    public void updateStatus(Long id, Long stid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", id);
        map.put("stid", stid);
        getSqlSession().update("Task.updateStatus", map);
    }

    @Override
    public void updatePriorityInST(Long tid, Long priority) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("priority", priority);
        map.put("tid", tid);
        getSqlSession().update("Task.updatePriorityInST", map);
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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("tid", tid);
        map.put("comment", comment);
        getSqlSession().insert("Task.commentTask", map);
    }

}
