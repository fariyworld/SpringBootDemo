package com.mace.domain;

import java.io.Serializable;
import java.util.Date;

public class Cart implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer user_id;

    /**
     * 商品id
     */
    private Integer product_id;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 是否选择,1=已勾选,0=未勾选
     */
    private Integer checked;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * mmall_cart
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @mbggenerated
     */
    public Cart(Integer id, Integer user_id, Integer product_id, Integer quantity, Integer checked, Date create_time, Date update_time) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.checked = checked;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    /**
     *
     * @mbggenerated
     */
    public Cart() {
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
     * 数量
     * @return quantity 数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 数量
     * @param quantity 数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 是否选择,1=已勾选,0=未勾选
     * @return checked 是否选择,1=已勾选,0=未勾选
     */
    public Integer getChecked() {
        return checked;
    }

    /**
     * 是否选择,1=已勾选,0=未勾选
     * @param checked 是否选择,1=已勾选,0=未勾选
     */
    public void setChecked(Integer checked) {
        this.checked = checked;
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
        sb.append(", product_id=").append(product_id);
        sb.append(", quantity=").append(quantity);
        sb.append(", checked=").append(checked);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}