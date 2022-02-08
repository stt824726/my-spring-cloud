package com.stt.api.user.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description 消费者参数类
 * @Author shaotongtong
 * @Date 2022-02-08 17:26
 */
public class EConsumer {

    /*
     * 公司类型  1：新用户  2： 老用户
     */
    @Getter
    @AllArgsConstructor
    public enum Type {
        NEWER(1, "新用户"),
        OLDER(2, "老用户");
        Integer value;
        String desc;
    }


}
