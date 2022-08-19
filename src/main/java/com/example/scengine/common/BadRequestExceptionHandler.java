package com.example.scengine.common;

import lombok.extern.java.Log;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 校验错误拦截处理
 *
 * @author tyn
 */
@RestControllerAdvice
@Log
public class BadRequestExceptionHandler {

    /**
     * 校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBean validationBodyException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        StringBuilder sb = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                sb.append(fieldError.getDefaultMessage()).append(";");
            });
        }
        //返回自定义错误格式
        return new ResultBean(ResultBean.FAIL, sb.toString());
    }

    /**
     * 参数类型转换错误
     *
     * @param exception 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResultBean parameterTypeException(HttpMessageConversionException exception) {
        log.info(exception.getCause().getLocalizedMessage());
        return new ResultBean(ResultBean.FAIL, "类型转换错误");
    }

}
