package com.mace.domain;

import java.io.Serializable;
import java.util.Date;

public class Shipping implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 收货姓名
     */
    private String receiver_name;

    /**
     * 收货固定电话
     */
    private String receiver_phone;

    /**
     * 收货移动电话
     */
    private String receiver_mobile;

    /**
     * 省份
     */
    private String receiver_province;

    /**
     * 城市
     */
    private String receiver_city;

    /**
     * 区/县
     */
    private String receiver_district;

    /**
     * 详细地址
     */
    private String receiver_address;

    /**
     * 邮编
     */
    private String receiver_zip;

    /**
     * 
     */
    private Date create_time;

    /**
     * 
     */
    private Date update_time;

    /**
     * mmall_shipping
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @mbggenerated
     */
    public Shipping(Integer id, Integer user_id, String receiver_name, String receiver_phone, String receiver_mobile, String receiver_province, String receiver_city, String receiver_district, String receiver_address, String receiver_zip, Date create_time, Date update_time) {
        this.id = id;
        this.user_id = user_id;
        this.receiver_name = receiver_name;
        this.receiver_phone = receiver_phone;
        this.receiver_mobile = receiver_mobile;
        this.receiver_province = receiver_province;
        this.receiver_city = receiver_city;
        this.receiver_district = receiver_district;
        this.receiver_address = receiver_address;
        this.receiver_zip = receiver_zip;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    /**
     *
     * @mbggenerated
     */
    public Shipping() {
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
     * 收货姓名
     * @return receiver_name 收货姓名
     */
    public String getReceiver_name() {
        return receiver_name;
    }

    /**
     * 收货姓名
     * @param receiver_name 收货姓名
     */
    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name == null ? null : receiver_name.trim();
    }

    /**
     * 收货固定电话
     * @return receiver_phone 收货固定电话
     */
    public String getReceiver_phone() {
        return receiver_phone;
    }

    /**
     * 收货固定电话
     * @param receiver_phone 收货固定电话
     */
    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone == null ? null : receiver_phone.trim();
    }

    /**
     * 收货移动电话
     * @return receiver_mobile 收货移动电话
     */
    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    /**
     * 收货移动电话
     * @param receiver_mobile 收货移动电话
     */
    public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile == null ? null : receiver_mobile.trim();
    }

    /**
     * 省份
     * @return receiver_province 省份
     */
    public String getReceiver_province() {
        return receiver_province;
    }

    /**
     * 省份
     * @param receiver_province 省份
     */
    public void setReceiver_province(String receiver_province) {
        this.receiver_province = receiver_province == null ? null : receiver_province.trim();
    }

    /**
     * 城市
     * @return receiver_city 城市
     */
    public String getReceiver_city() {
        return receiver_city;
    }

    /**
     * 城市
     * @param receiver_city 城市
     */
    public void setReceiver_city(String receiver_city) {
        this.receiver_city = receiver_city == null ? null : receiver_city.trim();
    }

    /**
     * 区/县
     * @return receiver_district 区/县
     */
    public String getReceiver_district() {
        return receiver_district;
    }

    /**
     * 区/县
     * @param receiver_district 区/县
     */
    public void setReceiver_district(String receiver_district) {
        this.receiver_district = receiver_district == null ? null : receiver_district.trim();
    }

    /**
     * 详细地址
     * @return receiver_address 详细地址
     */
    public String getReceiver_address() {
        return receiver_address;
    }

    /**
     * 详细地址
     * @param receiver_address 详细地址
     */
    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address == null ? null : receiver_address.trim();
    }

    /**
     * 邮编
     * @return receiver_zip 邮编
     */
    public String getReceiver_zip() {
        return receiver_zip;
    }

    /**
     * 邮编
     * @param receiver_zip 邮编
     */
    public void setReceiver_zip(String receiver_zip) {
        this.receiver_zip = receiver_zip == null ? null : receiver_zip.trim();
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
        sb.append(", receiver_name=").append(receiver_name);
        sb.append(", receiver_phone=").append(receiver_phone);
        sb.append(", receiver_mobile=").append(receiver_mobile);
        sb.append(", receiver_province=").append(receiver_province);
        sb.append(", receiver_city=").append(receiver_city);
        sb.append(", receiver_district=").append(receiver_district);
        sb.append(", receiver_address=").append(receiver_address);
        sb.append(", receiver_zip=").append(receiver_zip);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}