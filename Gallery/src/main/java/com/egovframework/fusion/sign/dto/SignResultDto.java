package com.egovframework.fusion.sign.dto;

public class SignResultDto {
    String msg;
    Boolean check;
    Integer id;
    String username;

    @Override
    public String toString() {
        return "SignResultDto{" +
                "msg='" + msg + '\'' +
                ", check=" + check +
                ", id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
