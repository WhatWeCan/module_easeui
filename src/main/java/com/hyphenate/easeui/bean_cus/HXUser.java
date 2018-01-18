package com.hyphenate.easeui.bean_cus;

import java.io.Serializable;

/**
 * 环信 账号信息
 * Created by tjstudy on 2018/1/17.
 */

public class HXUser implements Serializable {
    private String name;
    private String pwd;
    private String nick;
    private String headPic;

    public HXUser(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public HXUser(String name, String pwd, String nick) {
        this.name = name;
        this.pwd = pwd;
        this.nick = nick;
    }

    public HXUser(String name, String pwd, String nick, String headPic) {
        this.name = name;
        this.pwd = pwd;
        this.nick = nick;
        this.headPic = headPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
}
