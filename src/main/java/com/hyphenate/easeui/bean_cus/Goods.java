package com.hyphenate.easeui.bean_cus;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * 商品列表实体
 */

public class Goods implements Serializable {

    /**
     * goods_id : 21
     * cat_id : 13
     * goods_name : SONY 摄像机
     * store_count : 400
     * comment_count : 0
     * rent_price : 120.00
     * rent_way : 1
     * sales_sum : 0
     * original_img : http://www.zulincheng.com/Public/upload/goods/2017/06-30/5955d456ed695.jpg
     * deposit_price : 1000.00
     * distance : 1.3
     * type : 0
     */

    private int goods_id;
    private int cat_id;
    private String goods_name;
    private int store_count;
    private int comment_count;
    private String rent_price;
    private int rent_way;
    private int sales_sum;
    private String original_img;
    private String deposit_price;
    private double distance;
    private int type;
    private String goods_sn;//商家商品新增 订单号
    private String click_count;//浏览次数
    private String brand_id;//
    private String is_on_sale;//商品上下架，0 上架，1 下架

    //演艺人员 新增属性
    private String stature;//身高
    private String schedule;//档期(格式2017/7/至 2017/7/9)
    private String stagename;//艺名
    private String sex;//性别 0 女，1男
    private String age;//年龄
    private String easurements;//三围
    private String speciality;//特长


    public String getStature() {
        return stature;
    }

    public void setStature(String stature) {
        this.stature = stature;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getStagename() {
        return stagename;
    }

    public void setStagename(String stagename) {
        this.stagename = stagename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEasurements() {
        return easurements;
    }

    public void setEasurements(String easurements) {
        this.easurements = easurements;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getStore_count() {
        return store_count;
    }

    public void setStore_count(int store_count) {
        this.store_count = store_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getRent_price() {
        return TextUtils.isEmpty(rent_price) ? "0" : rent_price;
    }

    public void setRent_price(String rent_price) {
        this.rent_price = rent_price;
    }

    public int getRent_way() {
        return rent_way;
    }

    public void setRent_way(int rent_way) {
        this.rent_way = rent_way;
    }

    public int getSales_sum() {
        return sales_sum;
    }

    public void setSales_sum(int sales_sum) {
        this.sales_sum = sales_sum;
    }

    public String getOriginal_img() {
        return original_img;
    }

    public void setOriginal_img(String original_img) {
        this.original_img = original_img;
    }

    public String getDeposit_price() {
        return TextUtils.isEmpty(deposit_price) ? "0" : deposit_price;
    }

    public void setDeposit_price(String deposit_price) {
        this.deposit_price = deposit_price;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }

    public String getClick_count() {
        return click_count;
    }

    public void setClick_count(String click_count) {
        this.click_count = click_count;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getIs_on_sale() {
        return is_on_sale;
    }

    public void setIs_on_sale(String is_on_sale) {
        this.is_on_sale = is_on_sale;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goods_id=" + goods_id +
                ", cat_id=" + cat_id +
                ", goods_name='" + goods_name + '\'' +
                ", store_count=" + store_count +
                ", comment_count=" + comment_count +
                ", rent_price='" + rent_price + '\'' +
                ", rent_way=" + rent_way +
                ", sales_sum=" + sales_sum +
                ", original_img='" + original_img + '\'' +
                ", deposit_price='" + deposit_price + '\'' +
                ", distance=" + distance +
                ", type=" + type +
                ", goods_sn='" + goods_sn + '\'' +
                ", click_count='" + click_count + '\'' +
                ", brand_id='" + brand_id + '\'' +
                ", is_on_sale='" + is_on_sale + '\'' +
                '}';
    }
}
