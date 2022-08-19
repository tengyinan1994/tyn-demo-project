package com.example.scengine.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

/**
 * 分页基础类，包含排序和分页属性
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BasicPageCriteria {

    /**
     * 每页条数
     */
    private Integer pageSize = 10;
    /**
     * 当前页码
     */
    private Integer pageNum = 1;
    /**
     * 排序字段
     */
    private String orderBy;
    /**
     * 排序方式
     */
    private String orderType;



}
