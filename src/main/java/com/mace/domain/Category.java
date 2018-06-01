package com.mace.domain;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {
    /**
     * 类别Id
     */
    private Integer id;

    /**
     * 父类别id当id=0时说明是根节点,一级类别
     */
    private Integer parent_id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别状态1-正常,2-已废弃
     */
    private Boolean status;

    /**
     * 排序编号,同类展示顺序,数值相等则自然排序
     */
    private Integer sort_order;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * mmall_category
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @mbggenerated
     */
    public Category(Integer id, Integer parent_id, String name, Boolean status, Integer sort_order, Date create_time, Date update_time) {
        this.id = id;
        this.parent_id = parent_id;
        this.name = name;
        this.status = status;
        this.sort_order = sort_order;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    /**
     *
     * @mbggenerated
     */
    public Category() {
        super();
    }

    /**
     * 类别Id
     * @return id 类别Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 类别Id
     * @param id 类别Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 父类别id当id=0时说明是根节点,一级类别
     * @return parent_id 父类别id当id=0时说明是根节点,一级类别
     */
    public Integer getParent_id() {
        return parent_id;
    }

    /**
     * 父类别id当id=0时说明是根节点,一级类别
     * @param parent_id 父类别id当id=0时说明是根节点,一级类别
     */
    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    /**
     * 类别名称
     * @return name 类别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 类别名称
     * @param name 类别名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 类别状态1-正常,2-已废弃
     * @return status 类别状态1-正常,2-已废弃
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 类别状态1-正常,2-已废弃
     * @param status 类别状态1-正常,2-已废弃
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 排序编号,同类展示顺序,数值相等则自然排序
     * @return sort_order 排序编号,同类展示顺序,数值相等则自然排序
     */
    public Integer getSort_order() {
        return sort_order;
    }

    /**
     * 排序编号,同类展示顺序,数值相等则自然排序
     * @param sort_order 排序编号,同类展示顺序,数值相等则自然排序
     */
    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
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
        sb.append(", parent_id=").append(parent_id);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", sort_order=").append(sort_order);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}