package com.scrumers.storage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.scrumers.api.dao.IterationDao;
import com.scrumers.model.Iteration;
import com.scrumers.model.PlotData;

public class IterationDaoImpl extends SqlSessionDaoSupport implements
        IterationDao {

    @Override
    public void create(Iteration i) {
        getSqlSession().insert("Iteration.create", i);
    }

    @Override
    public Iteration read(Long id) {
        return getSqlSession().selectOne("Iteration.read", id);
    }

    @Override
    public List<Iteration> readAll() {
        return getSqlSession().selectList("Iteration.readAll");
    }

    @Override
    public List<Iteration> readByProductId(Long pid) {
        return getSqlSession().selectList("Iteration.readByProductId", pid);
    }

    @Override
    public void update(Iteration s) {
        getSqlSession().update("Iteration.update", s);
    }

    @Override
    public void delete(final Long id) {
        getSqlSession().delete("Iteration.delete", id);
    }

    @Override
    public void deleteFromProductIteration(Long id) {
        getSqlSession().delete("Iteration.deleteFromProductIteration", id);
    }

    @Override
    public void addStoryToAnIteration(Long iid, Long sid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("iid", iid);
        map.put("sid", sid);
        getSqlSession().insert("Iteration.addStoryToAnIteration", map);
    }

    @Override
    public Long selectId() {
        return getSqlSession().selectOne("Iteration.selectId");
    }

    @Override
    public void createWithId(Iteration i) {
        getSqlSession().insert("Iteration.createWithId", i);
    }

    @Override
    public void updatePriorityInIS(Long sid, Long priority) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("priority", priority);
        map.put("sid", sid);
        getSqlSession().update("Iteration.updatePriorityInIS", map);
    }

    @Override
    public Long readIterationHours(Long iid) {
        Long hours = getSqlSession().selectOne("Iteration.readIterationHours",
                iid);
        if (hours != null)
            return hours;
        else
            return 0L;
    }

    @Override
    public List<PlotData> readIterationDoneHours(Long iid) {
        return getSqlSession().selectList("Iteration.readIterationDoneHours",
                iid);
    }

    @Override
    public void addStoryToADone(Long iid, Long sid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("iid", iid);
        map.put("sid", sid);
        getSqlSession().insert("Iteration.addStoryToADone", map);
    }

    @Override
    public boolean readIterationStoryIsDone(Long iid, Long sid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("iid", iid);
        map.put("sid", sid);
        Long l = getSqlSession().selectOne(
                "Iteration.readIterationStoryIsDone", map);
        if (l != null) {
            return true;
        } else
            return false;
    }

    @Override
    public void deleteFromPlotIteration(Long iid, Long sid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("iid", iid);
        map.put("sid", sid);
        getSqlSession().insert("Iteration.deleteFromPlotIteration", map);
    }

    @Override
    public Long readAllHoursForToday(Long iid) {
        Long hours = getSqlSession().selectOne(
                "Iteration.readAllHoursForToday", iid);
        if (hours != null)
            return hours;
        else
            return 0L;
    }

    @Override
    public Long readAllDelHoursForToday(Long iid) {
        Long hours = getSqlSession().selectOne(
                "Iteration.readAllDelHoursForToday", iid);
        if (hours != null)
            return hours;
        else
            return 0L;
    }

    @Override
    public void addDelStoryToADone(Long iid, Long sid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("iid", iid);
        map.put("sid", sid);
        getSqlSession().insert("Iteration.addDelStoryToADone", map);
    }

    @Override
    public boolean readStoryHoursForToday(Long iid, Long sid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("iid", iid);
        map.put("sid", sid);
        Long l = getSqlSession().selectOne(
                "Iteration.readStoryHoursForToday", map);
        if (l != null) {
            return true;
        } else
            return false;
    }

}
