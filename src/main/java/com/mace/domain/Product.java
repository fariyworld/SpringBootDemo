package com.mace.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Product implements Serializable {
    /**
     * 商品id
     */
    private Integer id;

    /**
     * 分类id,对应mmall_category表的主键
     */
    private Integer category_id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品副标题
     */
    private String subtitle;

    /**
     * 产品主图,url相对地址
     */
    private String main_image;

    /**
     * 图片地址,json格式,扩展用
     */
    private String sub_images;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 价格,单位-元保留两位小数
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * 商品状态.1-在售 2-下架 3-删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * mmall_product
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @mbggenerated
     */
    public Product(Integer id, Integer category_id, String name, String subtitle, String main_image, String sub_images, String detail, BigDecimal price, Integer stock, Integer status, Date create_time, Date update_time) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.subtitle = subtitle;
        this.main_image = main_image;
        this.sub_images = sub_images;
        this.detail = detail;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    /**
     *
     * @mbggenerated
     */
    public Product() {
        super();
    }

    /**
     * 商品id
     * @return id 商品id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 商品id
     * @param id 商品id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 分类id,对应mmall_category表的主键
     * @return category_id 分类id,对应mmall_category表的主键
     */
    public Integer getCategory_id() {
        return category_id;
    }

    /**
     * 分类id,对应mmall_category表的主键
     * @param category_id 分类id,对应mmall_category表的主键
     */
    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    /**
     * 商品名称
     * @return name 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 商品名称
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 商品副标题
     * @return subtitle 商品副标题
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * 商品副标题
     * @param subtitle 商品副标题
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    /**
     * 产品主图,url相对地址
     * @return main_image 产品主图,url相对地址
     */
    public String getMain_image() {
        return main_image;
    }

    /**
     * 产品主图,url相对地址
     * @param main_image 产品主图,url相对地址
     */
    public void setMain_image(String main_image) {
        this.main_image = main_image == null ? null : main_image.trim();
    }

    /**
     * 图片地址,json格式,扩展用
     * @return sub_images 图片地址,json格式,扩展用
     */
    public String getSub_images() {
        return sub_images;
    }

    /**
     * 图片地址,json格式,扩展用
     * @param sub_images 图片地址,json格式,扩展用
     */
    public void setSub_images(String sub_images) {
        this.sub_images = sub_images == null ? null : sub_images.trim();
    }

    /**
     * 商品详情
     * @return detail 商品详情
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 商品详情
     * @param detail 商品详情
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /**
     * 价格,单位-元保留两位小数
     * @return price 价格,单位-元保留两位小数
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 价格,单位-元保留两位小数
     * @param price 价格,单位-元保留两位小数
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 库存数量
     * @return stock 库存数量
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 库存数量
     * @param stock 库存数量
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 商品状态.1-在售 2-下架 3-删除
     * @return status 商品状态.1-在售 2-下架 3-删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 商品状态.1-在售 2-下架 3-删除
     * @param status 商品状态.1-在售 2-下架 3-删除
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", category_id=").append(category_id);
        sb.append(", name=").append(name);
        sb.append(", subtitle=").append(subtitle);
        sb.append(", main_image=").append(main_image);
        sb.append(", sub_images=").append(sub_images);
        sb.append(", detail=").append(detail);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", status=").append(status);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}