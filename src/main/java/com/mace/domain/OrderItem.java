package com.mace.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderItem implements Serializable {
    /**
     * 订单子表id
     */
    private Integer id;

    /**
     * 
     */
    private Integer user_id;

    /**
     * 
     */
    private Long order_no;

    /**
     * 商品id
     */
    private Integer product_id;

    /**
     * 商品名称
     */
    private String product_name;

    /**
     * 商品图片地址
     */
    private String product_image;

    /**
     * 生成订单时的商品单价，单位是元,保留两位小数
     */
    private BigDecimal current_unit_price;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品总价,单位是元,保留两位小数
     */
    private BigDecimal total_price;

    /**
     * 
     */
    private Date create_time;

    /**
     * 
     */
    private Date update_time;

    /**
     * mmall_order_item
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @mbggenerated
     */
    public OrderItem(Integer id, Integer user_id, Long order_no, Integer product_id, String product_name, String product_image, BigDecimal current_unit_price, Integer quantity, BigDecimal total_price, Date create_time, Date update_time) {
        this.id = id;
        this.user_id = user_id;
        this.order_no = order_no;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_image = product_image;
        this.current_unit_price = current_unit_price;
        this.quantity = quantity;
        this.total_price = total_price;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    /**
     *
     * @mbggenerated
     */
    public OrderItem() {
        super();
    }

    /**
     * 订单子表id
     * @return id 订单子表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 订单子表id
     * @param id 订单子表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return user_id 
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * 
     * @param user_id 
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * 
     * @return order_no 
     */
    public Long getOrder_no() {
        return order_no;
    }

    /**
     * 
     * @param order_no 
     */
    public void setOrder_no(Long order_no) {
        this.order_no = order_no;
    }

    /**
     * 商品id
     * @return product_id 商品id
     */
    public Integer getProduct_id() {
        return product_id;
    }

    /**
     * 商品id
     * @param product_id 商品id
     */
    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    /**
     * 商品名称
     * @return product_name 商品名称
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * 商品名称
     * @param product_name 商品名称
     */
    public void setProduct_name(String product_name) {
        this.product_name = product_name == null ? null : product_name.trim();
    }

    /**
     * 商品图片地址
     * @return product_image 商品图片地址
     */
    public String getProduct_image() {
        return product_image;
    }

    /**
     * 商品图片地址
     * @param product_image 商品图片地址
     */
    public void setProduct_image(String product_image) {
        this.product_image = product_image == null ? null : product_image.trim();
    }

    /**
     * 生成订单时的商品单价，单位是元,保留两位小数
     * @return current_unit_price 生成订单时的商品单价，单位是元,保留两位小数
     */
    public BigDecimal getCurrent_unit_price() {
        return current_unit_price;
    }

    /**
     * 生成订单时的商品单价，单位是元,保留两位小数
     * @param current_unit_price 生成订单时的商品单价，单位是元,保留两位小数
     */
    public void setCurrent_unit_price(BigDecimal current_unit_price) {
        this.current_unit_price = current_unit_price;
    }

    /**
     * 商品数量
     * @return quantity 商品数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 商品数量
     * @param quantity 商品数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 商品总价,单位是元,保留两位小数
     * @return total_price 商品总价,单位是元,保留两位小数
     */
    public BigDecimal getTotal_price() {
        return total_price;
    }

    /**
     * 商品总价,单位是元,保留两位小数
     * @param total_price 商品总价,单位是元,保留两位小数
     */
    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 
     * @param create_time 
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * 
     * @return update_time 
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 
     * @param update_time 
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
        sb.append(", product_id=").append(product_id);
        sb.append(", product_name=").append(product_name);
        sb.append(", product_image=").append(product_image);
        sb.append(", current_unit_price=").append(current_unit_price);
        sb.append(", quantity=").append(quantity);
        sb.append(", total_price=").append(total_price);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}