package com.scrumers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Product extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String descr;

    private long owner;

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

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", name)
                .append("descr", descr)
                .append("owner", owner)
                .append("isDone", isDone)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Product)) {
            return false;
        }

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(owner, product.owner)
                .append(isDone, product.isDone)
                .append(name, product.name)
                .append(descr, product.descr)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(descr)
                .append(owner)
                .append(isDone)
                .toHashCode();
    }
}
