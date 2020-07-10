package com.hollycrm.mi.core.entity;

public class SysUser {
    private String user_code;
    private String user_name;
    private String org_id;
    private String password;
    private String pwd_valid_day;   //yyyy-MM-dd
    private String mobile;
    private String email;
    private String address;
    private Integer enabled;
    private String remark;
    private String creator;
    private String create_time;
    private String last_modifier;
    private String last_modify_time;

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwd_valid_day() {
        return pwd_valid_day;
    }

    public void setPwd_valid_day(String pwd_valid_day) {
        this.pwd_valid_day = pwd_valid_day;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLast_modifier() {
        return last_modifier;
    }

    public void setLast_modifier(String last_modifier) {
        this.last_modifier = last_modifier;
    }

    public String getLast_modify_time() {
        return last_modify_time;
    }

    public void setLast_modify_time(String last_modify_time) {
        this.last_modify_time = last_modify_time;
    }
}
