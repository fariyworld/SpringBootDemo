package com.mace.domain;

import java.io.Serializable;
import java.util.Date;

public class PayInfo implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 订单号
     */
    private Long order_no;

    /**
     * 支付平台:1-支付宝,2-微信
     */
    private Integer pay_platform;

    /**
     * 支付宝支付流水号
     */
    private String platform_number;

    /**
     * 支付宝支付状态
     */
    private String platform_status;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * mmall_pay_info
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @mbggenerated
     */
    public PayInfo(Integer id, Integer user_id, Long order_no, Integer pay_platform, String platform_number, String platform_status, Date create_time, Date update_time) {
        this.id = id;
        this.user_id = user_id;
        this.order_no = order_no;
        this.pay_platform = pay_platform;
        this.platform_number = platform_number;
        this.platform_status = platform_status;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    /**
     *
     * @mbggenerated
     */
    public PayInfo() {
        super();
    }

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户id
     * @return user_id 用户id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * 用户id
     * @param user_id 用户id
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * 订单号
     * @return order_no 订单号
     */
    public Long getOrder_no() {
        return order_no;
    }

    /**
     * 订单号
     * @param order_no 订单号
     */
    public void setOrder_no(Long order_no) {
        this.order_no = order_no;
    }

    /**
     * 支付平台:1-支付宝,2-微信
     * @return pay_platform 支付平台:1-支付宝,2-微信
     */
    public Integer getPay_platform() {
        return pay_platform;
    }

    /**
     * 支付平台:1-支付宝,2-微信
     * @param pay_platform 支付平台:1-支付宝,2-微信
     */
    public void setPay_platform(Integer pay_platform) {
        this.pay_platform = pay_platform;
    }

    /**
     * 支付宝支付流水号
     * @return platform_number 支付宝支付流水号
     */
    public String getPlatform_number() {
        return platform_number;
    }

    /**
     * 支付宝支付流水号
     * @param platform_number 支付宝支付流水号
     */
    public void setPlatform_number(String platform_number) {
        this.platform_number = platform_number == null ? null : platform_number.trim();
    }

    /**
     * 支付宝支付状态
     * @return platform_status 支付宝支付状态
     */
    public String getPlatform_status() {
        return platform_status;
    }

    /**
     * 支付宝支付状态
     * @param platform_status 支付宝支付状态
     */
    public void setPlatform_status(String platform_status) {
        this.platform_status = platform_status == null ? null : platform_status.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 创建时间
     * @param create_time 创建时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 更新时间
     * @param update_time 更新时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    /**
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", user_id=").append(user_id);
        sb.append(", order_no=").append(order_no);
        sb.append(", pay_platform=").append(pay_platform);
        sb.append(", platform_number=").append(platform_number);
        sb.append(", platform_status=").append(platform_status);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}