package com.SydShp.twibber;

import java.util.Calendar;

public class Twibber {
    private int userID;
    String username;
    String content;
    int year;
    int month;
    int day;
    int hour;
    int minute;

    public Twibber(String username, String content, int year, int month, int day, int hour, int minute) {
        this.username = username;
        this.content = content;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public String getTime(){
        Calendar calendar = Calendar.getInstance();
        String t="";
        if(calendar.get(calendar.YEAR)==year){
            if(calendar.get(calendar.MONTH)+1==month&&calendar.get(calendar.DAY_OF_MONTH)==day){
                if(calendar.get(calendar.MINUTE)==minute){
                    t+="刚刚";
                }else {
                    t+=hour;
                    t+=":";
                    t+=minute;
                }
            }else {
                t+=month;
                t+="月";
                t+=day;
                t+="日";
            }
        }else {
            t+=year;
            t+="年";
            t+=month;
            t+="月";
            t+=day;
            t+="日";
        }
        return t;
    }
}
