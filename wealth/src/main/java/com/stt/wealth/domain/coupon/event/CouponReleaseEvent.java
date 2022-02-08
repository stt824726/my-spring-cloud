package com.stt.wealth.domain.coupon.event;

import com.stt.common.validator.group.AddGroup;
import com.stt.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description 优惠券发布事件
 * @Author shaotongtong
 * @Date 2022-02-08 18:58
 */
@Data
@ApiModel(value="优惠券发布",description="优惠券发布")
public class CouponReleaseEvent {

    /**
     * id
     */
    @ApiModelProperty(value = "优惠券id")
    @NotBlank(message="优惠券id不能为空", groups = UpdateGroup.class)
    private String id;

    /**
     * 优惠券名称
     */
    @ApiModelProperty(value = "优惠券名称")
    @NotBlank(message="优惠券名称" , groups = AddGroup.class)
    private String name;

    /**
     * 适用标签
     */
    @ApiModelProperty("适用标签")
    private String tag;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    private String icon;

    /**
     * 使用类型：1-满减券
     */
    @ApiModelProperty("使用类型：1-满减券")
    private String type;


    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid")
    @NotBlank(message="uuid不能为空")
    private String uuid;


}
