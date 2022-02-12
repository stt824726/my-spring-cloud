package com.stt.constant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 统一请求响应消息体
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_MSG = "操作成功";
    public static final int FAIL_CODE = 1;
    public static final String FAIL_MSG = "操作失败";
    private int result;
    @JsonIgnore
    private HttpStatus HttpStatus;
    private String desc;

    private T data;

    public Result(T v) {
        data = v;
    }

    public Result() {
    }

    public Result(int result, String desc) {
        this.result = result;
        this.desc = desc;
    }

    public Result(int result, String desc, T data) {
        this.result = result;
        this.desc = desc;
        this.data = data;
    }

    public Result(int result, org.springframework.http.HttpStatus httpStatus, String desc) {
        super();
        this.result = result;
        HttpStatus = httpStatus;
        this.desc = desc;
    }

    /**
     * 接口调用成功返回，传入需要返回的result数据
     *
     * @param data
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> baseResult = new Result<T>(data);
        baseResult.setResult(SUCCESS_CODE);
        baseResult.setDesc(SUCCESS_MSG);
        return baseResult;
    }

    /**
     * 接口调用成功返回，传入需要返回的result数据
     *
     * @param data
     * @return
     */
    public static <T> Result<T> successData(T data) {
        Result<T> baseResult = new Result<T>(data);
        baseResult.setResult(SUCCESS_CODE);
        baseResult.setDesc(SUCCESS_MSG);
        return baseResult;
    }

    public static Result success() {
        Result baseResult = new Result();
        baseResult.setResult(SUCCESS_CODE);
        baseResult.setDesc(SUCCESS_MSG);
        return baseResult;
    }

    public static <T> Result<T> success(String desc) {
        Result<T> baseResult = new Result<T>();
        baseResult.setResult(SUCCESS_CODE);
        baseResult.setDesc(desc);
        return baseResult;
    }

    /**
     * 失败返回
     *
     * @return
     */
    public static <T> Result<T> failResult(String desc) {
        return new Result<T>(Result.FAIL_CODE, desc, null);
    }

    /**
     * 失败返回
     *
     * @return
     */
    public static <T> Result<T> failResult(String desc, T t) {
        return new Result<T>(Result.FAIL_CODE, desc, t);
    }

    /**
     * 失败返回
     *
     * @return
     */
    public static <T> Result<T> failResult(int code, String desc) {
        return new Result<T>(code, desc, null);
    }

    /**
     * 失败返回
     *
     * @return
     */
    public static <T> Result<T> failResult(ResultCode resultCode) {
        return new Result<T>(resultCode.getResult(), resultCode.getHttpStatus(), resultCode.getDesc());
    }

    /**
     * 失败返回
     *
     * @return
     */
    public static <T> Result<T> failResult() {
        return new Result<T>(Result.FAIL_CODE, FAIL_MSG, null);
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" + "result='" + result + '\'' + ", desc='" + desc + '\'' + ", data=" + data + '}';
    }

    /**
     * 判断接口返回成功或者失败
     * @return true 接口业务成功 fasle 接口业务失败
     */
    public Boolean isSuccess(){
        return this.getResult() == SUCCESS_CODE;
    }

}
