package com.mardefasma.influaction_java.api.model;

import java.util.Date;

public class Endorse {
    private User cust;
    private Platform platform;
    private Date begin;
    private Date end;

    public User getCust() {
        return cust;
    }

    public void setCust(User cust) {
        this.cust = cust;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Endorse(User cust, Platform platform, Date begin, Date end) {
        this.cust = cust;
        this.platform = platform;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Endorse{" +
                "cust=" + cust +
                ", platform=" + platform +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
