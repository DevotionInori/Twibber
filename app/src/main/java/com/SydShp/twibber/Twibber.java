package com.SydShp.twibber;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Twibber {
    private String username;
    private String content;
    private Date date;
    private int id;
    private int publisherID;
    private int likeCount;
    private int transferCount;

    public Twibber(String username, String content) {
        this.username = username;
        this.content = content;
        this.date=new Date();
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public String getTime(){
        Date dateNow=new Date();
        long delta=dateNow.getTime()-date.getTime();
        //Calendar calendar = Calendar.getInstance();
        String t="";
        if(delta<60000){
            t+="刚刚";
        }else if(delta<3600*1000){
            long d = delta/6000;
            t+=d;
            t+="分钟前";
        }else if(delta<3600*10*1000){
            long d = delta/(3600*1000);
            t+=d;
            t+="小时前";
        }else{
            SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
            SimpleDateFormat hm = new SimpleDateFormat("hh:mm");
            if(yyyy.format(date)!=yyyy.format(dateNow)){
                t+=yyyy.format(date);
                t+="年";
            }
            if(ymd.format(date)!=ymd.format(dateNow)){
                SimpleDateFormat md = new SimpleDateFormat("MM月dd日");
                t+=md.format(date);
            }else {
                t += hm.format(date);
            }
        }
        return t;
//        if(calendar.get(calendar.YEAR)==year){
//            if(calendar.get(calendar.MONTH)+1==month&&calendar.get(calendar.DAY_OF_MONTH)==day){
//                if(calendar.get(calendar.MINUTE)==minute){
//                    t+="刚刚";
//                }else {
//                    t+=hour;
//                    t+=":";
//                    t+=minute;
//                }
//            }else {
//                t+=month;
//                t+="月";
//                t+=day;
//                t+="日";
//            }
//        }else {
//            t+=year;
//            t+="年";
//            t+=month;
//            t+="月";
//            t+=day;
//            t+="日";
//        }
//        return t;
    }
}
