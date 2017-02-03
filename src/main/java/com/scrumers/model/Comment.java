package com.scrumers.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Date;

public class Comment extends Entity {

    private static final long serialVersionUID = 1L;

    private String text;

    private String login;

    private String name;

    private Long userId;

    private Date dateComment;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("text", text)
                .append("login", login)
                .append("name", name)
                .append("userId", userId)
                .append("dateComment", dateComment)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(text)
                .append(login)
                .append(name)
                .append(userId)
                .append(dateComment)
                .toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Comment)) {
            return false;
        }

        Comment comment = (Comment) o;

        return new EqualsBuilder()
                .append(text, comment.text)
                .append(login, comment.login)
                .append(name, comment.name)
                .append(userId, comment.userId)
                .append(dateComment, comment.dateComment)
                .isEquals();
    }

}
