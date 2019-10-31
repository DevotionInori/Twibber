package com.SydShp.twibber.model;

import org.litepal.crud.LitePalSupport;

public class UserRelation extends LitePalSupport {

    private int id;
    private int userId;
    private int followId;

    //Auto generated getter and setter below
    public int getId() {
        return id;
   }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }



}
