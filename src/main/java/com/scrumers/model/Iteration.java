package com.scrumers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class Iteration extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private String name;
    private String descr;
    private Date dateStart;
    private Date dateEnd;
    private long iterationNum;
    private long productId;
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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", name)
                .append("descr", descr)
                .append("dateStart", dateStart)
                .append("dateEnd", dateEnd)
                .append("iterationNum", iterationNum)
                .append("isDone", isDone)
                .append("productId", productId)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Iteration)) {
            return false;
        }

        Iteration iteration = (Iteration) o;

        return new EqualsBuilder()
                .append(iterationNum, iteration.iterationNum)
                .append(isDone, iteration.isDone)
                .append(name, iteration.name)
                .append(descr, iteration.descr)
                .append(dateStart, iteration.dateStart)
                .append(dateEnd, iteration.dateEnd)
                .append(productId, iteration.productId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(descr)
                .append(dateStart)
                .append(dateEnd)
                .append(iterationNum)
                .append(isDone)
                .append(productId)
                .toHashCode();
    }

}
