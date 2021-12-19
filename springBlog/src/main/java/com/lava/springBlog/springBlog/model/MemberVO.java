package com.lava.springBlog.springBlog.model;

public class MemberVO {

    private Long id;

    private String userID;
    private String userPWD;
    private String userPWD_Check;
    private String userName;
    private String userPhone;

    public MemberVO() {

    }

    public MemberVO(Long id, String userID, String userPWD, String userPWD_Check, String userName, String userPhone) {
        this.id = id;
        this.userID = userID;
        this.userPWD = userPWD;
        this.userPWD_Check = userPWD_Check;
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPWD() {
        return userPWD;
    }

    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }

    public String getUserPWD_Check() {
        return userPWD_Check;
    }

    public void setUserPWD_Check(String userPWD_Check) {
        this.userPWD_Check = userPWD_Check;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
