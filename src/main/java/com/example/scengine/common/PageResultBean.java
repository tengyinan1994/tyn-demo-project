package com.example.scengine.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PageResultBean<T> implements Serializable {
    /**
     * //每页显示几条记录
     */
    private int pageSize = 20;
    /**
     * 总记录数
     */
    private long totalElements;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 页号
     */
    private int pageNum;
    /**
     * 当前有几条记录
     */
    private int count;

    private static final long serialVersionUID = 1L;
    public static final String NO_LOGIN = "-1";
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String NO_PERMISSION = "2";
    private String returnMsg = "success";
    private String returnCode = SUCCESS;

    @JsonUnwrapped
    private T list;

    public PageResultBean(){
    }

    public PageResultBean(T t){
        this.list = t;
    }

    public PageResultBean(Page page){
        this.setPageNum(page.getPageNum());
        this.setPageSize(page.getPageSize());
        this.setTotalElements(page.getTotal());
        this.setTotalPages(page.getPages());
        this.setCount(page.size());
        this.setList((T) page.getResult());

    }

    public PageResultBean(PageInfo<T> page) {
        this.setPageNum(page.getPageNum());
        this.setPageSize(page.getPageSize());
        this.setTotalElements(page.getTotal());
        this.setTotalPages(page.getPages());
        this.setCount(page.getSize());
        this.setList((T)page.getList());
    }

}
