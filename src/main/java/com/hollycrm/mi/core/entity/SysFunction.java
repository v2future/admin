package com.hollycrm.mi.core.entity;

public class SysFunction {

    private String function_id;
    private String function_name;
    private String parent_function_id;
    private String url;
    private String view_level_no;
    private String function_type;
    private Integer sort_no;
    private Integer enabled;
    private String remark;
    private String creator;
    private String create_time;
    private String last_modifier;
    private String last_modify_time;
    private String permission_key;

    public String getFunction_id() {
        return function_id;
    }

    public void setFunction_id(String function_id) {
        this.function_id = function_id;
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public String getParent_function_id() {
        return parent_function_id;
    }

    public void setParent_function_id(String parent_function_id) {
        this.parent_function_id = parent_function_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getView_level_no() {
        return view_level_no;
    }

    public void setView_level_no(String view_level_no) {
        this.view_level_no = view_level_no;
    }

    public String getFunction_type() {
        return function_type;
    }

    public void setFunction_type(String function_type) {
        this.function_type = function_type;
    }

    public Integer getSort_no() {
        return sort_no;
    }

    public void setSort_no(Integer sort_no) {
        this.sort_no = sort_no;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
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

    public String getPermission_key() {
        return permission_key;
    }

    public void setPermission_key(String permission_key) {
        this.permission_key = permission_key;
    }
}
