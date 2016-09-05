package com.scrumers.model;

public class Role extends Entity {

    private static final long serialVersionUID = 1L;

    private String name;

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String toString() {
        return new StringBuilder("role: {id:").append(getId()).append(" name:")
                .append(getName()).append("}").toString();
    }
}
