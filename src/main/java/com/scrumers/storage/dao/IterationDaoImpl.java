package com.scrumers.storage.dao;

import static com.google.common.base.MoreObjects.firstNonNull;

import java.util.List;
import com.google.common.collect.ImmutableMap;
import com.scrumers.api.dao.IterationDao;
import com.scrumers.model.Iteration;
import com.scrumers.model.PlotData;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class IterationDaoImpl extends SqlSessionDaoSupport implements IterationDao {

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
    public void addStoryToAnIteration(Long iid, Long sid) {
        getSqlSession().insert("Iteration.addStoryToAnIteration", ImmutableMap.of("iid", iid, "sid", sid));
    }

    @Override
    public void updatePriorityInIS(Long sid, Long priority) {
        getSqlSession().update("Iteration.updatePriorityInIS", ImmutableMap.of("priority", priority, "sid", sid));
    }

    @Override
    public Long readIterationHours(Long iid) {
        Long hours = getSqlSession().selectOne("Iteration.readIterationHours", iid);
        return firstNonNull(hours, 0L);
    }

    @Override
    public List<PlotData> readIterationDoneHours(Long iid) {
        return getSqlSession().selectList("Iteration.readIterationDoneHours", iid);
    }

    @Override
    public void addStoryToADone(Long iid, Long sid) {
        getSqlSession().insert("Iteration.addStoryToADone", ImmutableMap.of("iid", iid, "sid", sid));
    }

    @Override
    public boolean readIterationStoryIsDone(Long iid, Long sid) {
        Long l = getSqlSession().selectOne("Iteration.readIterationStoryDone", ImmutableMap.of("iid", iid, "sid", sid));
        return l != null;
    }

    @Override
    public void deleteFromPlotIteration(Long iid, Long sid) {
        getSqlSession().insert("Iteration.deleteFromPlotIteration", ImmutableMap.of("iid", iid, "sid", sid));
    }

    @Override
    public Long readAllHoursForToday(Long iid) {
        Long hours = getSqlSession().selectOne("Iteration.readAllHoursForToday", iid);
        return firstNonNull(hours, 0L);
    }

    @Override
    public Long readAllDelHoursForToday(Long iid) {
        Long hours = getSqlSession().selectOne("Iteration.readAllDelHoursForToday", iid);
        return firstNonNull(hours, 0L);
    }

    @Override
    public void addDelStoryToADone(Long iid, Long sid) {
        getSqlSession().insert("Iteration.addDelStoryToADone", ImmutableMap.of("iid", iid, "sid", sid));
    }

    @Override
    public boolean readStoryHoursForToday(Long iid, Long sid) {
        Long l = getSqlSession().selectOne("Iteration.readStoryHoursForToday", ImmutableMap.of("iid", iid, "sid", sid));
        return l != null;
    }

}
