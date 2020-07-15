package com.hollycrm.mi.core.bean;

import java.util.List;

/**
 * LayUI分页信息
 * @param <T>
 */
public class Pager<T> {

    private String code = "0";
    private boolean success = false;
    private String msg;

    protected int pageNo = 1;

    //protected long totalPages = -1;

    protected int pageSize = 10;

    protected int count = -1;

    protected List<T> data = null;

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
        if (count < 0) {
            return -1;
        }
        int totalPages = count / pageSize;
        if (count % pageSize > 0) {
            totalPages++;
        }
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
