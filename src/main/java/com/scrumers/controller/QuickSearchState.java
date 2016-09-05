package com.scrumers.controller;

import java.io.Serializable;

public class QuickSearchState implements Serializable {
    private static final long serialVersionUID = 1L;

    private String search;

    private Integer page;

    private String type;

    public QuickSearchState() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(final String search) {
        this.search = search;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(final Integer page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
