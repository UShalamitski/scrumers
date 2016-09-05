package com.scrumers.api.service;

import java.util.List;

import com.scrumers.model.Iteration;
import com.scrumers.model.PlotData;

public interface IterationService {

    Iteration getIteration(Long id);

    void updatePriorityInIS(Long[] ids);

    void saveIteration(Iteration i, Long pid);

    void deleteIteration(Long[] id);

    void deleteIteration(Long id);

    List<Iteration> getIterations();

    List<Iteration> getIterationsByProductId(Long pid);

    List<PlotData> getIterationPlotData(Long iid);

    void addStoryToAnIteration(Long iid, Long sid);

    void addStoryToADone(Long iid, Long sid);

}