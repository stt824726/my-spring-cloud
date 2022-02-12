package com.stt.constant;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResultCode {

    private final int result;

    private final HttpStatus httpStatus;

    private final String desc;

    public ResultCode(int result, HttpStatus httpStatus, String desc) {
        super();
        this.result = result;
        this.httpStatus = httpStatus;
        this.desc = desc;
    }

}
