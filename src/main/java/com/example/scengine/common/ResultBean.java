package com.example.scengine.common;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String NO_LOGIN = "-1";
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String NO_PERMISSION = "2";
    private String returnMsg = "success";
    private String returnCode = SUCCESS;

    @JsonUnwrapped
    private T data;

    public ResultBean() {
    }

    public ResultBean(String returnCode, String returnMsg) {
        this.returnMsg = returnMsg;
        this.returnCode = returnCode;
    }

    public ResultBean(T t) {
        this.data = t;
    }

    public ResultBean(Throwable e) {
        super();
        this.returnMsg = e.toString();
        this.returnCode = FAIL;
    }
}
