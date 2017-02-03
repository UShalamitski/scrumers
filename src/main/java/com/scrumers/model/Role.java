package com.scrumers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Role extends Entity {

    private static final long serialVersionUID = 1L;

    private String name;

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", name)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Role)) {
            return false;
        }

        Role role = (Role) o;

        return new EqualsBuilder()
                .append(name, role.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .toHashCode();
    }
}
