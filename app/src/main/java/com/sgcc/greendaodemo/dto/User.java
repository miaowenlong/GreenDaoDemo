package com.sgcc.greendaodemo.dto;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by miao_wenlong on 2017/6/15.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long _id;
    @NotNull
    private String name;
    private String pwd;
    private boolean rem_pwd;
    private boolean auto_login;
    @Generated(hash = 185332282)
    public User(Long _id, @NotNull String name, String pwd, boolean rem_pwd,
            boolean auto_login) {
        this._id = _id;
        this.name = name;
        this.pwd = pwd;
        this.rem_pwd = rem_pwd;
        this.auto_login = auto_login;
    }
    @Generated(hash = 586692638)
    public User() {
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return this.pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public boolean getRem_pwd() {
        return this.rem_pwd;
    }
    public void setRem_pwd(boolean rem_pwd) {
        this.rem_pwd = rem_pwd;
    }
    public boolean getAuto_login() {
        return this.auto_login;
    }
    public void setAuto_login(boolean auto_login) {
        this.auto_login = auto_login;
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(long _id) {
        this._id = _id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }

}
