package com.scrumers.service;

import com.scrumers.api.dao.IterationDao;
import com.scrumers.api.dao.ProductDao;
import com.scrumers.api.dao.StoryDao;
import com.scrumers.api.service.StoryService;
import com.scrumers.model.Comment;
import com.scrumers.model.Story;
import com.scrumers.model.enums.StoryStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryDao storyDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private IterationDao iterationDao;

    @Override
    public void saveStory(Story s, Long pid, Long iid) {
        if (s.getId() == null) {
            Long sid = storyDao.selectId();
            s.setId(sid);
            if (s.getStatus() == null) {
                s.setStatus(StoryStatusEnum.TODO);
            }
            storyDao.createWithId(s, pid);
            if (pid != null)
                productDao.addStoryToAProduct(pid, sid);
            if (iid != null)
                iterationDao.addStoryToAnIteration(iid, sid);
            if (s.getStatus() == StoryStatusEnum.DONE)
                iterationDao.addStoryToADone(iid, sid);
        } else {
            storyDao.update(s);
        }
    }

    @Override
    public void deleteStory(Long[] id) {
        if (id != null) {
            for (Long i : id) {
                deleteStory(i);
            }
        }
    }

    @Override
    public void deleteStory(Long sid) {
        if (sid != null) {
            storyDao.delete(sid);
        }
    }

    @Override
    public void deleteStory(Long sid, Long iid) {
        if (sid != null) {
            storyDao.delete(sid);
            iterationDao.deleteFromPlotIteration(iid, sid);
        }
    }

    @Override
    public void deleteStoryFromIteration(Long id) {
        if (id != null) {
            storyDao.deleteFromIterationStory(id);
        }
    }

    @Override
    public List<Story> getStories() {
        return storyDao.readAll();
    }

    @Override
    public Story getStory(Long id) {
        return storyDao.read(id);
    }

    @Override
    public Story getStoryByTaskId(Long tid) {
        return storyDao.readByTaskId(tid);
    }

    @Override
    public List<Story> getStoriesByProductId(Long pid) {
        return storyDao.readByProductId(pid);
    }

    @Override
    public List<Story> getStoriesByIterationId(Long iid) {
        return storyDao.readByIterationId(iid);
    }

    @Override
    public void addTaskToAStory(Long sid, Long tid) {
        storyDao.addTaskToAStory(sid, tid);
    }

    @Override
    public void addUserToAStory(Long sid, Long uid) {
        storyDao.addUserToAStory(sid, uid);
    }

    @Override
    public void updateStatus(Long id, StoryStatusEnum status) {
        storyDao.updateStatus(id, status);
    }

    @Override
    public void updateStatus(Long sid, Long iid, StoryStatusEnum status) {
        storyDao.updateStatus(sid, status);
        if (iterationDao.readIterationStoryIsDone(iid, sid)) {
            iterationDao.deleteFromPlotIteration(iid, sid);
        } else {
            if (!iterationDao.readStoryHoursForToday(iid, sid))
                iterationDao.addDelStoryToADone(iid, sid);
        }
    }

    @Override
    public void updateStatusToDone(Long sid, Long iid, StoryStatusEnum status) {
        storyDao.updateStatus(sid, status);
        if (!iterationDao.readIterationStoryIsDone(iid, sid)) {
            if (!iterationDao.readStoryHoursForToday(iid, sid)) {
                iterationDao.addStoryToADone(iid, sid);
            } else {
                iterationDao.deleteFromPlotIteration(iid, sid);
            }
        }
    }

    @Override
    public void updatePriorities(StoryStatusEnum status, Long iid, Long[] ids) {
        List<Long> idd = storyDao.readPriorities(iid, status);

        if (ids.length == idd.size()) {
            for (int i = 0; i < ids.length; i++) {
                iterationDao.updatePriorityInIS(ids[i], idd.get(i));
            }
        }
    }

    @Override
    public List<Story> getUnusedStoriesFromBacklog(Long pid) {
        return storyDao.readUnusedStoriesFromBacklog(pid);
    }

    @Override
    public void updatePrioritiesOfUnuserdStoriesFromBcklog(Long pid, Long[] ids) {
        List<Long> idd = storyDao.readPrioritiesOfUnusedStoriesFromBacklog(pid);

        if (ids.length == idd.size()) {
            for (int i = 0; i < ids.length; i++)
                productDao.updatePriorityInPS(ids[i], idd.get(i));
        }
    }

    @Override
    public boolean IterationStoryIsDone(Long iid, Long sid) {
        return iterationDao.readIterationStoryIsDone(iid, sid);
    }

    @Override
    public void comment(Long uid, Long sid, String comment) {
        storyDao.comment(uid, sid, comment);
    }

    @Override
    public Comment getComment(Long cid) {
        if (cid != null) {
            return storyDao.findComment(cid);
        } else {
            return new Comment();
        }
    }

    @Override
    public void deleteComment(Long cid, Long uid) {
        storyDao.deleteComment(cid, uid);
    }

    @Override
    public List<Comment> readCommentsByStoryId(Long sid) {
        return storyDao.readCommentsByStoryId(sid);
    }

}
