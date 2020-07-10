package com.hollycrm.mi.core.bean;

import java.util.List;

/**
 * 分页信息
 * @param <T>
 */
public class Pager<T> {

    protected int pageNo = 1;

    //protected long totalPages = -1;

    protected int pageSize = 10;

    protected int totalCount = -1;

    protected List<T> list = null;

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        if (pageNo < 1) {
            this.pageNo = 1;
        }
    }

    public long getTotalPages() {
        if (totalCount < 0) {
            return -1;
        }
        int totalPages = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            totalPages++;
        }
        return totalPages;
    }

    /*
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    */

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
