package com.netcracker.springhomework.dto;

import java.time.LocalDateTime;

public class UserWithMeta {
    private User user;
    private String agent;
    private long date;

    public UserWithMeta(User user, String agent, long date) {
        this.user = user;
        this.agent = agent;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
