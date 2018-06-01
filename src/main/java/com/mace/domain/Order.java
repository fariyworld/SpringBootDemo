package com.mace.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    /**
     * 订单id
     */
    private Integer id;

    /**
     * 订单号
     */
    private Long order_no;

    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 
     */
    private Integer shipping_id;

    /**
     * 实际付款金额,单位是元,保留两位小数
     */
    private BigDecimal payment;

    /**
     * 支付类型,1-在线支付
     */
    private Integer payment_type;

    /**
     * 运费,单位是元
     */
    private Integer postage;

    /**
     * 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
     */
    private Integer status;

    /**
     * 支付时间
     */
    private Date payment_time;

    /**
     * 发货时间
     */
    private Date send_time;

    /**
     * 交易完成时间
     */
    private Date end_time;

    /**
     * 交易关闭时间
     */
    private Date close_time;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * mmall_order
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @mbggenerated
     */
    public Order(Integer id, Long order_no, Integer user_id, Integer shipping_id, BigDecimal payment, Integer payment_type, Integer postage, Integer status, Date payment_time, Date send_time, Date end_time, Date close_time, Date create_time, Date update_time) {
        this.id = id;
        this.order_no = order_no;
        this.user_id = user_id;
        this.shipping_id = shipping_id;
        this.payment = payment;
        this.payment_type = payment_type;
        this.postage = postage;
        this.status = status;
        this.payment_time = payment_time;
        this.send_time = send_time;
        this.end_time = end_time;
        this.close_time = close_time;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    /**
     *
     * @mbggenerated
     */
    public Order() {
        super();
    }

    /**
     * 订单id
     * @return id 订单id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 订单id
     * @param id 订单id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 
     * @return shipping_id 
     */
    public Integer getShipping_id() {
        return shipping_id;
    }

    /**
     * 
     * @param shipping_id 
     */
    public void setShipping_id(Integer shipping_id) {
        this.shipping_id = shipping_id;
    }

    /**
     * 实际付款金额,单位是元,保留两位小数
     * @return payment 实际付款金额,单位是元,保留两位小数
     */
    public BigDecimal getPayment() {
        return payment;
    }

    /**
     * 实际付款金额,单位是元,保留两位小数
     * @param payment 实际付款金额,单位是元,保留两位小数
     */
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    /**
     * 支付类型,1-在线支付
     * @return payment_type 支付类型,1-在线支付
     */
    public Integer getPayment_type() {
        return payment_type;
    }

    /**
     * 支付类型,1-在线支付
     * @param payment_type 支付类型,1-在线支付
     */
    public void setPayment_type(Integer payment_type) {
        this.payment_type = payment_type;
    }

    /**
     * 运费,单位是元
     * @return postage 运费,单位是元
     */
    public Integer getPostage() {
        return postage;
    }

    /**
     * 运费,单位是元
     * @param postage 运费,单位是元
     */
    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    /**
     * 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
     * @return status 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
     * @param status 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 支付时间
     * @return payment_time 支付时间
     */
    public Date getPayment_time() {
        return payment_time;
    }

    /**
     * 支付时间
     * @param payment_time 支付时间
     */
    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    /**
     * 发货时间
     * @return send_time 发货时间
     */
    public Date getSend_time() {
        return send_time;
    }

    /**
     * 发货时间
     * @param send_time 发货时间
     */
    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    /**
     * 交易完成时间
     * @return end_time 交易完成时间
     */
    public Date getEnd_time() {
        return end_time;
    }

    /**
     * 交易完成时间
     * @param end_time 交易完成时间
     */
    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    /**
     * 交易关闭时间
     * @return close_time 交易关闭时间
     */
    public Date getClose_time() {
        return close_time;
    }

    /**
     * 交易关闭时间
     * @param close_time 交易关闭时间
     */
    public void setClose_time(Date close_time) {
        this.close_time = close_time;
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
        sb.append(", order_no=").append(order_no);
        sb.append(", user_id=").append(user_id);
        sb.append(", shipping_id=").append(shipping_id);
        sb.append(", payment=").append(payment);
        sb.append(", payment_type=").append(payment_type);
        sb.append(", postage=").append(postage);
        sb.append(", status=").append(status);
        sb.append(", payment_time=").append(payment_time);
        sb.append(", send_time=").append(send_time);
        sb.append(", end_time=").append(end_time);
        sb.append(", close_time=").append(close_time);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}