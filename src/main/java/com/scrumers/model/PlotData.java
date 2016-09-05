package com.scrumers.model;

import java.util.Date;

public class PlotData {

    private Long id;

    private Long hours;

    private Date date;

    private Long delHours;

    public PlotData() {
    }

    public PlotData(Long _hours, Date _date) {
        hours = _hours;
        date = _date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public Long getDelHours() {
        return delHours;
    }

    public void setDelHours(Long delHours) {
        this.delHours = delHours;
    }

}
