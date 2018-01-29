package com.example.yueuy.dream;

/**
 * Created by yueuy on 18-1-29.
 */

public class UserData {
    private String account;
    private String password;
    private int userId;
    private String portrait;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public UserData(String userName, String userPassword){
        this.account = userName;
        this.password = userPassword;
    }
}
