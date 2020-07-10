package com.hollycrm.mi.core.mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper <T> {

    T get(String id);

    void deleteById(String id);

    void insert(T entity);

    void update(T entity);

    void updateByExample(T example);

    Integer selectCount(Map<String, Object> param);

    List<T> selectList(Map<String, Object> param);
}
