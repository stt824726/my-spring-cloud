package com.stt.api.user.fo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program:
 * @description: 用户信息list
 * @author: shaotongtong
 * @create:
 **/
@Data
public class UserBaseFO implements Serializable {

    private static final long serialVersionUID = -1455616948314585310L;

    private Integer id;

    //类型
    private Integer type;

    //0:男;1:女;
    private Integer sex;

    //账号
    private String acc;

    //姓名
    private String name;

    //昵称
    private String nickname;

    //电话
    private String phone;

    //地址
    private String address;

    //0,禁用：1，1启用
    private Integer status;

    //备注
    private String remark;

    //生日日期
    private Date birthday;

    /**
     *  用户来源：1，后台录入,2，开放注册,3，转介绍
     */
    private Integer agent;

    //是否删除（0:正常,1:删除）
    private Integer delStatus;

    //创建人id
    private Integer createUserId;

}
