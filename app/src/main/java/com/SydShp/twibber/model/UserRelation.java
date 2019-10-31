package com.SydShp.twibber.model;

import org.litepal.crud.LitePalSupport;

public class UserRelation extends LitePalSupport {

    private int id;
    private String userId;
    private String followId;

    //Auto generated getter and setter below
    public int getId() {
        return id;
   }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }



}
