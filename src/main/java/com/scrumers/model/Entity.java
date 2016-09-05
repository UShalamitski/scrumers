package com.scrumers.model;

import java.io.Serializable;

public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    public final Long getId() {
        return id;
    }

    public final void setId(final Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }
}
