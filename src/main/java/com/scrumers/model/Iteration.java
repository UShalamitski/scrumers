package com.scrumers.model;

import java.util.Date;

public class Iteration extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String descr;

    private Date dateStart;

    private Date dateEnd;

    private long iterationNum;

    private boolean isDone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public long getIterationNum() {
        return iterationNum;
    }

    public void setIterationNum(long iterationNum) {
        this.iterationNum = iterationNum;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
