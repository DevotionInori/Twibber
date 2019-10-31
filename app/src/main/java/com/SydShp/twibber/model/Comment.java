package com.SydShp.twibber.model;

import org.litepal.crud.LitePalSupport;

public class Comment extends LitePalSupport {

    private int id;
    private int twibberId;
    private String comment;
    private int commenterId;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(int commenterId) {
        this.commenterId = commenterId;
    }
}
