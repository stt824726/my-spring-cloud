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


    //微信id
    private String openId;

    //类型(0: 平台 1：车主 2:客户经理；3，管理员;4:店长，5：经销商，6：大大汽配用户)
    private Integer type;

    //0:男;1:女;
    private Integer sex;

    //账号
    private String acc;

    //密码
    private String pwd;

    //邮箱
    private String email;

    /** 联系开始时间 */
    private String contactStartTime;

    /** 联系截止时间 */
    private String contactEndTime;

    //头像地址
    private String avatar;

    //从业年限
    private String workYear;

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

    //推荐人
    private Integer recommendedUserId;

    //备注
    private String remark;

    //
    private Date createTime;


    private Date updateTime;

    //生日日期
    private Date birthday;
    /**
     *  用户来源：1，后台录入,2，开放注册,3，兆信导入,4，社群迁移，5-转介绍
     */
    private Integer agent;

    //最近扫描二维码门店
    private Integer scanDefaultCompanyId;

    //手动设置门店
    private Integer defaultCompanyId;

    //是否删除（0:正常,1:删除）
    private Integer delStatus;

    //微信号
    private String wxCode;

    //门店角色ID
    private Integer garageRoleId;

    //微信用户多平台统一id
    private String unionId;

    //创建人id
    private Integer createUserId;

    //会员卡手机号（车主用户）
    private String memberCardPhone;
    private String invitationStatus;
}
