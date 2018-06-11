package com.mace.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * description: 返回前端的购物车对象
 * <br />
 * Created by mace on 18:13 2018/6/8.
 */
public class CartVo {

    // 离线购物车 outerKey
    private String outerKey;
    // 购物车所属用户 ID
    private Integer userId;
    // 购物车商品列表
    private List<CartProductVo> cartProductVoList;
    // 购物车总计
    private BigDecimal cartTotalPrice;
    // 是否已经都勾选
    private Boolean allChecked;

    public String getOuterKey() {
        return outerKey;
    }

    public void setOuterKey(String outerKey) {
        this.outerKey = outerKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<CartProductVo> getCartProductVoList() {
        return cartProductVoList;
    }

    public void setCartProductVoList(List<CartProductVo> cartProductVoList) {
        this.cartProductVoList = cartProductVoList;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Boolean getAllChecked() {
        return allChecked;
    }

    public void setAllChecked(Boolean allChecked) {
        this.allChecked = allChecked;
    }
}
