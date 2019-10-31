package com.SydShp.twibber.model;

import org.litepal.LitePal;

public class Content extends LitePal {

    private int id;
    private int twibberId;
    private String content;



    private String contenterId;

    //Auto generated getter and setter below

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTwibberId() {
        return twibberId;
    }

    public void setTwibberId(int twibberId) {
        this.twibberId = twibberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContenterId() {
        return contenterId;
    }

    public void setContenterId(String contenterId) {
        this.contenterId = contenterId;
    }
}
