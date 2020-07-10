package com.hollycrm.mi.core.service;

import com.hollycrm.mi.core.bean.Pager;
import com.hollycrm.mi.core.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface UserService {

    SysUser get(String id);

    void deleteById(String id);

    void insert(SysUser sysUser);

    void update(SysUser sysUser);

    void updateByExample(SysUser example);

    Integer selectCount(Map<String, Object> param);

    List<SysUser> selectList(Map<String, Object> param);

    /**
     * 分页
     * @param param
     * @param startPage
     * @param pageSize
     * @return
     */
    Pager page(Map<String, Object> param, int startPage, int pageSize);

}
