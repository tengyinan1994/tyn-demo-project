package com.example.scengine.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getNoLogin() {
        return NO_LOGIN;
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public static String getFAIL() {
        return FAIL;
    }

    public static String getNoPermission() {
        return NO_PERMISSION;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResultBean{" +
                "pageSize=" + pageSize +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", pageNum=" + pageNum +
                ", count=" + count +
                ", returnMsg='" + returnMsg + '\'' +
                ", returnCode='" + returnCode + '\'' +
                ", list=" + list +
                '}';
    }
}
