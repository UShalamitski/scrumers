package com.scrumers.model;

import java.util.Date;

public abstract class AbstractEntity extends Entity{

    private static final long serialVersionUID = 1L;

    private Long idCreator;

    private Date added;

    private Date modified;

    private boolean del;

    public Long getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(final Long idCreator) {
        this.idCreator = idCreator;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(final Date added) {
        this.added = added;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(final Date modified) {
        this.modified = modified;
    }

    public boolean getDel() {
        return del;
    }

    public void setDel(final boolean del) {
        this.del = del;
    }
}
