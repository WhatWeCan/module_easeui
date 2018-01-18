package com.hyphenate.easeui.bean_cus;

/**
 * 用户
 */

public class User {

    /**
     * user_id : 2
     * password : 519475228fe35ad067744465c42a19b2
     * sex : 1
     * address_id : 2
     * reg_time : 1498526016
     * last_login : 1499483968
     * last_ip :
     * mobile : 13760323687
     * mobile_validated : 1
     * oauth :
     * openid :
     * head_pic : www.zulincheng.com/Public/upload/goods/2017/06-26/5950b1ed5cd0b.jpg
     * nickname : 仰望云端
     * token : 0b0433f97ce08433aeb22fc9a3129aa0
     */

    private int user_id;//用户ID
    private String password;//密码
    private int sex;//性别0保密1男2女
    private int address_id;//地址ID
    private int reg_time;//注册时间
    private int last_login;//最后一次登录
    private String last_ip;//最后一次登录的IP
    private String mobile;//手机
    private int mobile_validated;//是否验证手机
    private String oauth;//第三方来源
    private String openid;//第三方唯一标识
    private String head_pic;//头像
    private String nickname;//昵称
    private String token;
    private String user_money;//用户余额
    private String id_card_no;//身份证号
    private String business_licence_number;//营业执照号
    private String bank_number;//银行卡号
    private String is_supplier;//是否申请了商家入驻，0 否，1 是
    private String supplier_type;//当 is_supplier 为 1 时 才有    入驻类型 0 商家，1 自营，2    个人
    private String supplier_status;//当 is_supplier 为 1 时 才有    审核状态 -1 审核未通过，1    是审核通过，2 是审核中
    private String real_name;//银行开户名
    private String city;//用户所在城市
    private String deal_password;//交易密码'
    private String bank_name;//银行名字

    /**
     * 是否申请了商家入驻.
     * 是否申请了商家入驻，0 否，1 是
     *
     * @return
     */
    public boolean isSupplier() {
        return "1".equals(is_supplier);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public String getSexStr() {
        if (sex == 1) {
            return "男";
        }
        if (sex == 2) {
            return "女";
        }
        return "保密";
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getReg_time() {
        return reg_time;
    }

    public void setReg_time(int reg_time) {
        this.reg_time = reg_time;
    }

    public int getLast_login() {
        return last_login;
    }

    public void setLast_login(int last_login) {
        this.last_login = last_login;
    }

    public String getLast_ip() {
        return last_ip;
    }

    public void setLast_ip(String last_ip) {
        this.last_ip = last_ip;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getMobile_validated() {
        return mobile_validated;
    }

    public void setMobile_validated(int mobile_validated) {
        this.mobile_validated = mobile_validated;
    }

    public String getOauth() {
        return oauth;
    }

    public void setOauth(String oauth) {
        this.oauth = oauth;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_money() {
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public String getId_card_no() {
        return id_card_no;
    }

    public void setId_card_no(String id_card_no) {
        this.id_card_no = id_card_no;
    }

    public String getBusiness_licence_number() {
        return business_licence_number;
    }

    public void setBusiness_licence_number(String business_licence_number) {
        this.business_licence_number = business_licence_number;
    }

    public String getBank_number() {
        return bank_number;
    }

    public void setBank_number(String bank_number) {
        this.bank_number = bank_number;
    }

    public String getIs_supplier() {
        return is_supplier;
    }

    public void setIs_supplier(String is_supplier) {
        this.is_supplier = is_supplier;
    }

    public String getSupplier_type() {
        return supplier_type;
    }

    public void setSupplier_type(String supplier_type) {
        this.supplier_type = supplier_type;
    }

    public String getSupplier_status() {
        return supplier_status;
    }

    public void setSupplier_status(String supplier_status) {
        this.supplier_status = supplier_status;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDeal_password() {
        return deal_password;
    }

    public void setDeal_password(String deal_password) {
        this.deal_password = deal_password;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", address_id=" + address_id +
                ", reg_time=" + reg_time +
                ", last_login=" + last_login +
                ", last_ip='" + last_ip + '\'' +
                ", mobile='" + mobile + '\'' +
                ", mobile_validated=" + mobile_validated +
                ", oauth='" + oauth + '\'' +
                ", openid='" + openid + '\'' +
                ", head_pic='" + head_pic + '\'' +
                ", nickname='" + nickname + '\'' +
                ", token='" + token + '\'' +
                ", user_money='" + user_money + '\'' +
                ", id_card_no='" + id_card_no + '\'' +
                ", business_licence_number='" + business_licence_number + '\'' +
                ", bank_number='" + bank_number + '\'' +
                ", is_supplier='" + is_supplier + '\'' +
                ", supplier_type='" + supplier_type + '\'' +
                ", supplier_status='" + supplier_status + '\'' +
                ", real_name='" + real_name + '\'' +
                ", city='" + city + '\'' +
                ", deal_password='" + deal_password + '\'' +
                ", bank_name='" + bank_name + '\'' +
                '}';
    }
}
