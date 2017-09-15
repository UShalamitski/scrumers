package com.scrumers.service;

import com.scrumers.api.dao.IterationDao;
import com.scrumers.api.dao.ProductDao;
import com.scrumers.api.service.IterationService;
import com.scrumers.model.Iteration;
import com.scrumers.model.PlotData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class IterationServiceImpl implements IterationService {

    @Autowired
    private IterationDao iterationDao;
    @Autowired
    private ProductDao prouctDao;

    @Override
    public void saveIteration(Iteration i) {
        if (i.getId() == null) {
            iterationDao.create(i);
        } else {
            iterationDao.update(i);
        }
    }

    @Override
    public void deleteIteration(Long[] id) {
        for (long i : id) {
            iterationDao.delete(i);
        }
    }

    @Override
    public void deleteIteration(Long id) {
        if (id != null) {
            iterationDao.delete(id);
        }
    }

    @Override
    public List<Iteration> getIterations() {
        return iterationDao.readAll();
    }

    @Override
    public Iteration getIteration(Long id) {
        return iterationDao.read(id);
    }

    @Override
    public List<Iteration> getIterationsByProductId(Long pid) {
        return iterationDao.readByProductId(pid);
    }

    @Override
    public void addStoryToAnIteration(Long iid, Long sid) {
        iterationDao.addStoryToAnIteration(iid, sid);
    }

    @Override
    public void updatePriorityInIS(Long[] ids) {
        long n = 0L;
        for (long id : ids) {
            iterationDao.updatePriorityInIS(id, ++n);
        }
    }

    @Override
    public List<PlotData> getIterationPlotData(Long iid) {
        try {
            if (iid != null) {
                Long hours = iterationDao.readIterationHours(iid);
                Iteration i = iterationDao.read(iid);
                Long delHours = iterationDao.readAllDelHoursForToday(iid);
                Date start = i.getDateStart();
                Date end = i.getDateEnd();
                Long h = hours;
                Calendar scal = Calendar.getInstance();
                Calendar ecal = Calendar.getInstance();
                scal.setTime(start);
                ecal.setTime(end);

                List<PlotData> data1 = iterationDao.readIterationDoneHours(iid);
                List<PlotData> data = new ArrayList<PlotData>();

                Calendar today = Calendar.getInstance();
                today.set(Calendar.HOUR_OF_DAY, 0);
                today.set(Calendar.MINUTE, 0);
                today.set(Calendar.SECOND, 0);
                today.set(Calendar.MILLISECOND, 0);

                for (PlotData pr : data1) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(pr.getDate());

                    while (scal.compareTo(c) < 0) {
                        data.add(new PlotData(h, scal.getTime()));
                        scal.add(Calendar.DAY_OF_YEAR, 1);
                    }

                    if (scal.compareTo(today) == 0) {
                        if (delHours <= 0)
                            h = h - iterationDao.readAllHoursForToday(iid);
                        else
                            h = h + delHours
                                    - iterationDao.readAllHoursForToday(iid);
                    } else
                        h = h - pr.getHours();

                    data.add(new PlotData(h, pr.getDate()));
                    scal.add(Calendar.DAY_OF_YEAR, 1);
                }

                while (scal.compareTo(today) <= 0) {

                    if (scal.compareTo(ecal) == 0) {
                        data.add(new PlotData(h, scal.getTime()));
                        scal.add(Calendar.DAY_OF_YEAR, 1);
                        break;
                    }
                    if (scal.compareTo(today) == 0) {
                        if (delHours > 0) {
                            h = h + delHours
                                    - iterationDao.readAllHoursForToday(iid);
                        }
                    }
                    data.add(new PlotData(h, scal.getTime()));
                    scal.add(Calendar.DAY_OF_YEAR, 1);
                }

                data.add(new PlotData(hours, start));
                data.add(new PlotData(0L, end));
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addStoryToADone(Long iid, Long sid) {
        if (iid != null && sid != null) {
            iterationDao.addStoryToADone(iid, sid);
        }
    }

}
