package com.example.scengine.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PageResultBean<T> implements Serializable {

    /**
     * 每页显示几条记录
     */
    private int pageSize = 20;

    /**
     * 总记录数
     */
    private long totalElements;

    /**
     * 总页数
     */
    private long totalPages;

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

    private String returnMessage = "success";

    private String returnCode = SUCCESS;

    private Map<String, Object> otherData;

    @JsonAnyGetter
    public Map<String, Object> getOtherData() {
        return otherData;
    }

    private List<T> list;

    public PageResultBean() {
    }

    public PageResultBean(List<T> t) {
        this.list = t;
    }

    public PageResultBean(Page<T> page) {
        this.setPageNum(page.getPageNum());
        this.setPageSize(page.getPageSize());
        this.setTotalElements(page.getTotal());
        this.setTotalPages(page.getPages());
        this.setCount(page.size());
        this.setList(page.getResult());

    }

    public PageResultBean(PageInfo<T> page) {
        this.setPageNum(page.getPageNum());
        this.setPageSize(page.getPageSize());
        this.setTotalElements(page.getTotal());
        this.setTotalPages(page.getPages());
        this.setCount(page.getSize());
        this.setList(page.getList());
    }

    public <R, E extends IPage<R>> PageResultBean(E page, Function<R, T> change) {
        this.pageNum = Math.toIntExact(page.getCurrent());
        this.pageSize = Math.toIntExact(page.getSize());
        this.totalElements = page.getTotal();
        this.totalPages = page.getPages();
        this.list = page.getRecords().stream().map(change).collect(Collectors.toList());
        this.count = Math.toIntExact(this.list.size());
    }

    public <E extends IPage<T>> PageResultBean(E page) {
        this.pageNum = Math.toIntExact(page.getCurrent());
        this.pageSize = Math.toIntExact(page.getSize());
        this.totalElements = page.getTotal();
        this.totalPages = page.getPages();
        this.list = page.getRecords();
        this.count = Math.toIntExact(this.list.size());
    }

    public PageResultBean<T> addOtherData(String key, Object obj) {
        if (otherData == null) {
            otherData = new HashMap<>();
        }
        otherData.put(key, obj);
        return this;
    }

}
