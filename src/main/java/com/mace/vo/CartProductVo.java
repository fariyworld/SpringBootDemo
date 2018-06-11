package com.mace.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

/**
 * description: 前端 添加购物车时 需把这些信息传递到 controller 层
 *              封装到 CartProductVo 对象中 保存至 cookie 对应的 outerKey 的 Redis库中。
 * 1. 所属组 店铺、自营 innerKey
 * 2. CartProductVo   innerValue
 * <br />
 * Created by mace on 14:04 2018/6/9.
 */
public class CartProductVo {

    /**
     * 店铺 id
     */
    private String shopId;

    /**
     * 商品 id
     */
    private Integer productId;

    /**
     * 商品 name
     */
    private String productName;
    /**
     * 商品 小标题
     */
    private String productSubtitle;

    /**
     * 商品 type(颜色、品种、规格)
     */
    private String productType;

    /**
     * 商品 单价
     */
    private BigDecimal productPrice;

    /**
     * 商品 数量
     */
    private Integer count;

    /**
     * 小计
     */
    private BigDecimal subTotalPrice;

    /**
     * 商品 图片 url
     */
    private String productMainImage;

    /**
     * 商品是否勾选 1 勾选 0 未勾选
     */
    private Integer productChecked;


    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSubtitle() {
        return productSubtitle;
    }

    public void setProductSubtitle(String productSubtitle) {
        this.productSubtitle = productSubtitle;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getSubTotalPrice() {
        return subTotalPrice;
    }

    public void setSubTotalPrice(BigDecimal subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }

    public String getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(String productMainImage) {
        this.productMainImage = productMainImage;
    }

    public Integer getProductChecked() {
        return productChecked;
    }

    public void setProductChecked(Integer productChecked) {
        this.productChecked = productChecked;
    }


    // 只比较 productType
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CartProductVo that = (CartProductVo) o;

        return new EqualsBuilder()
                .append(productType, that.productType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(productType)
                .toHashCode();
    }
}
