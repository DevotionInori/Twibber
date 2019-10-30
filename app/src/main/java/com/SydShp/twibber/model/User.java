package com.SydShp.twibber.model;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport {

    private int id;

    @Column(unique = true)
    private String name;

    @Column(unique = true,nullable = false)
    private String loginId;
    @Column(nullable = false)
    private String passWd;

    @Column(defaultValue = "0")
    private int follow_count;
    @Column(defaultValue = "0")
    private int fans_count;


    //Auto generated getter and setter below
    public int getFollow_count() {
        return follow_count;
    }

    public void setFollow_count(int follow_count) {
        this.follow_count = follow_count;
    }

    public int getFans_count() {
        return fans_count;
    }

    public void setFans_count(int fans_count) {
        this.fans_count = fans_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

}
