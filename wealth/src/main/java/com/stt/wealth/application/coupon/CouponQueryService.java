package com.stt.wealth.application.coupon;

import com.stt.constant.FeignResult;

/**
 * @Description 优惠券查询服务接口
 * @Author shaotongtong
 * @Date 2022-02-08 20:35
 */
public interface CouponQueryService {

    FeignResult<Long> release();
}
