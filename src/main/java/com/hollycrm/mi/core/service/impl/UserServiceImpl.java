package com.hollycrm.mi.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hollycrm.mi.core.bean.Pager;
import com.hollycrm.mi.core.entity.SysUser;
import com.hollycrm.mi.core.mapper.UserMapper;
import com.hollycrm.mi.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser get(String id) {
        return userMapper.get(id);
    }

    @Override
    public void deleteById(String id) {
        userMapper.deleteById(id);
    }

    @Override
    public void insert(SysUser sysUser) {
        userMapper.insert(sysUser);
    }

    /**
     * 如果某属性为null，会过滤
     * @param sysUser
     */
    @Override
    public void update(SysUser sysUser) {
        userMapper.update(sysUser);
    }

    /**
     * 如果example某个属性为null，会把表该字段设为null
     * @param example
     */
    @Override
    public void updateByExample(SysUser example) {
        userMapper.updateByExample(example);
    }

    @Override
    public Integer selectCount(Map<String, Object> param) {
        return userMapper.selectCount( param);
    }

    @Override
    public List<SysUser> selectList(Map<String, Object> param) {
        return userMapper.selectList( param);
    }

    @Override
    public Pager page(Map<String, Object> param, int startPage, int pageSize) {
        Pager result = new Pager();
        result.setPageNo( startPage);
        result.setPageSize( pageSize);
        PageHelper.startPage(startPage, pageSize);
        Page list = (Page)userMapper.selectList( param);
        result.setList( list);
        result.setTotalCount( new Long(list.getTotal()).intValue());
        return result;
    }
}
