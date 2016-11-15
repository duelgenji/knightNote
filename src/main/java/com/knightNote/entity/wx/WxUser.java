package com.knightNote.entity.wx;

import com.wonders.xlab.framework.entity.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 微信用户表
 * Created by duelgenji on 16/11/14.
 */
@Entity
@Table
public class WxUser extends AbstractBaseEntity<Long> {

    //当前公众号 用户的唯一标识
    private String openId;

    //网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
    private String accessToken;

    //用户的昵称
    private String nickname;

    private String headimgurl;

    //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private Integer sex;

    //用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
    private int subscribe;

    //用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
    private Long subscribe_time;

    //用户所在的分组ID（兼容旧的用户分组接口）
    private Long groupid;

    //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
    private String unionid;

    //公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
    private String remark;

    //用户被打上的标签ID列表
    private String tagid_list;

    //用户的语言，简体中文为zh_CN
    private String language;

    //省份
    private String province;

    //城市
    private String city;

    //国家
    private String country;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public Long getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(Long subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(String tagid_list) {
        this.tagid_list = tagid_list;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
