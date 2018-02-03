package com.example.yueuy.dream.data.user;

/**
 * Created by yueuy on 18-1-31.
 */

public class UserData {
    private int usa;    //用户参与的所有故事数
    private int usb;    //用户发起的所有故事数
    private int userlikenum;      //用户获得点赞数
    private int userwords;     //用户总字数

    public int getUsa() {
        return usa;
    }

    public void setUsa(int usa) {
        this.usa = usa;
    }

    public int getUsb() {
        return usb;
    }

    public void setUsb(int usb) {
        this.usb = usb;
    }

    public int getUserlikenum() {
        return userlikenum;
    }

    public void setUserlikenum(int userlikenum) {
        this.userlikenum = userlikenum;
    }

    public int getUserwords() {
        return userwords;
    }

    public void setUserwords(int userwords) {
        this.userwords = userwords;
    }
}
