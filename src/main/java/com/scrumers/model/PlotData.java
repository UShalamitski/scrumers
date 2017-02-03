package com.scrumers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("hours", hours)
                .append("date", date)
                .append("delHours", delHours)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof PlotData)) {
            return false;
        }

        PlotData plotData = (PlotData) o;

        return new EqualsBuilder()
                .append(id, plotData.id)
                .append(hours, plotData.hours)
                .append(date, plotData.date)
                .append(delHours, plotData.delHours)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(hours)
                .append(date)
                .append(delHours)
                .toHashCode();
    }
}
