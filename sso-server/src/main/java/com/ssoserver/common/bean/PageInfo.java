package com.ssoserver.common.bean;

import java.io.Serializable;

/**
 * 分页信息
 *
 * @author LEEMER
 * Create Date: 2019-10-11
 */
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private Long total;
    /**
     * 总页数
     */
    private Long totalPages;
    /**
     * 当前页
     */
    private Integer pageNo;
    /**
     * 页记录数
     */
    private Integer pageSize;

    public PageInfo(Long total) {
        this.total = total;
    }

    public PageInfo(Long total, Integer pageNo,
                    Integer pageSize) {
        this.total = total;
        this.totalPages = (total - 1) / pageSize + 1;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public static PageInfo page(Long total) {
        return new PageInfo(total);
    }

    public static PageInfo page( Long total, Integer pageNo,
                                 Integer pageSize) {
        return new PageInfo(total, pageNo, pageSize);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
