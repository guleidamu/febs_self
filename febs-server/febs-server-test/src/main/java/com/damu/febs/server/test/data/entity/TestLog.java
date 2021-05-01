package com.damu.febs.server.test.data.entity;

import java.io.Serializable;
import java.util.Date;

public class TestLog implements Serializable{
    private Integer id;

    private String logcontent;

    private Date createts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogcontent() {
        return logcontent;
    }

    public void setLogcontent(String logcontent) {
        this.logcontent = logcontent == null ? null : logcontent.trim();
    }

    public Date getCreatets() {
        return createts;
    }

    public void setCreatets(Date createts) {
        this.createts = createts;
    }
}