package com.scrumers.service;

import java.util.List;

import com.scrumers.api.dao.StoryDao;
import com.scrumers.api.dao.TaskDao;
import com.scrumers.api.service.TaskService;
import com.scrumers.model.Comment;
import com.scrumers.model.Task;

public class TaskServiceImpl implements TaskService {

    private TaskDao taskDao;

    private StoryDao storyDao;

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public void setStoryDao(StoryDao storyDao) {
        this.storyDao = storyDao;
    }

    @Override
    public void saveTask(Task t) {
        if (t.getId() == null) {
            taskDao.create(t);
        } else {
            taskDao.update(t);
        }
    }

    @Override
    public void saveTask(Task t, Long sid) {
        if (t.getId() == null) {
            Long tid = taskDao.selectId();
            t.setId(tid);
            taskDao.createWithId(t, sid);
            if (sid != null)
                storyDao.addTaskToAStory(sid, tid);
        } else {
            taskDao.update(t);
        }
    }

    @Override
    public void updateStatus(Long id, Long stid) {
        taskDao.updateStatus(id, stid);
    }

    @Override
    public void updatePriorities(Long stat_id, Long sid, Long[] ids) {
        if (ids != null) {
            List<Long> idd = taskDao.readPriorities(sid, stat_id);
            if (ids.length == idd.size()) {
                for (int i = 0; i < ids.length; i++) {
                    taskDao.updatePriorityInST(ids[i], idd.get(i));
                }
            }
        }
    }

    @Override
    public void deleteTask(Long[] id) {
        if (id != null)
            for (Long i : id) {
                // taskDao.deleteFromStoryTasks(i);
                taskDao.delete(i);
            }
    }

    @Override
    public void deleteTask(Long id) {
        if (id != null) {
            // taskDao.deleteFromStoryTasks(id);
            taskDao.delete(id);
        }

    }

    @Override
    public List<Task> getTasks() {
        return taskDao.readAll();
    }

    @Override
    public Task getTask(Long id) {
        return taskDao.read(id);
    }

    @Override
    public List<Task> getTasksByStoryId(Long sid) {
        return taskDao.readByStoryId(sid);
    }

    @Override
    public void commentTask(Long uid, Long tid, String comment) {
        if (uid != null && tid != null && !comment.equals(""))
            taskDao.commentTask(uid, tid, comment);
    }

    @Override
    public void deleteComment(Long cid) {
        if (cid != null) {
            taskDao.deleteComment(cid);
        }
    }

    @Override
    public List<Comment> readCommentsByTaskId(Long tid) {
        if (tid != null)
            return taskDao.readCommentsByTaskId(tid);
        else
            return null;

    }
}
