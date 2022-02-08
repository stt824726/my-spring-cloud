package com.stt.constant;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author shaotongtong
 * @Date 2022-02-08 20:27
 */
@Data
public class FeignResult<T> implements Serializable {

    private int code;

    private String desc;

    private T data;

    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_MSG = "操作成功";

    public static final int FAIL_CODE = 1;
    public static final String FAIL_MSG = "操作失败";

    public FeignResult() {}
    public FeignResult(int code, String desc, T data) {
        super();
        this.code = code;
        this.desc = desc;
        this.data = data;
    }


    /**
     * 根据code==0判断是否成功
     * @return  是否成功
     */
    public boolean isSuccess() {
        return code == 0;
    }


}
