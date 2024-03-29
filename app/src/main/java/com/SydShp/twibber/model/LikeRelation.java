package com.SydShp.twibber.model;

import org.litepal.crud.LitePalSupport;

public class LikeRelation extends LitePalSupport {

    private int id;
    private int twibberId;
    private int contenterId;

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

    public int getContenterId() {
        return contenterId;
    }

    public void setContenterId(int contenterId) {
        this.contenterId = contenterId;
    }

}
