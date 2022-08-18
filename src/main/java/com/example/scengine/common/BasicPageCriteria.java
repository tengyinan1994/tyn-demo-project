package com.example.scengine.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * 分页基础类，包含排序和分页属性
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicPageCriteria {

    private Integer pageSize = 10;
    private Integer pageNum = 1;
    private String orderBy;    //排序字段
    private String orderType;  //排序方式

    private List<String> tenantIdListInRepo;
    private List<String> projectIdListInRepo;
    private List<String> deptIdListInRepo;
    private List<String> areaIdListInRepo;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<String> getTenantIdListInRepo() {
        return tenantIdListInRepo;
    }

    public void setTenantIdListInRepo(List<String> tenantIdListInRepo) {
        this.tenantIdListInRepo = tenantIdListInRepo;
    }

    public List<String> getProjectIdListInRepo() {
        return projectIdListInRepo;
    }

    public void setProjectIdListInRepo(List<String> projectIdListInRepo) {
        this.projectIdListInRepo = projectIdListInRepo;
    }

    public List<String> getDeptIdListInRepo() {
        return deptIdListInRepo;
    }

    public void setDeptIdListInRepo(List<String> deptIdListInRepo) {
        this.deptIdListInRepo = deptIdListInRepo;
    }

    public List<String> getAreaIdListInRepo() {
        return areaIdListInRepo;
    }

    public void setAreaIdListInRepo(List<String> areaIdListInRepo) {
        this.areaIdListInRepo = areaIdListInRepo;
    }
}
