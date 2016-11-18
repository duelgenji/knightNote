package com.knightNote.entity.wx;

import com.wonders.xlab.framework.entity.AbstractBaseEntity;

import javax.persistence.*;

/**
 * 微信菜单
 * Created by duelgenji on 16/11/15.
 */
@Entity
@Table
public class WxMenu extends AbstractBaseEntity<Long> {

    //必须 菜单标题，不超过16个字节，子菜单不超过60个字节
    private String menuName;

    //必须 菜单的响应动作类型
    private String menuType;

    //click等点击类型必须 ;菜单KEY值，用于消息接口推送，不超过128字节
    private String menuKey;

    //iew类型必须 ;网页链接，用户点击菜单可打开链接，不超过1024字节
    private String menuUrl;

    //media_id类型和view_limited类型必须; 调用新增永久素材接口返回的合法media_id
    private String media_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private WxMenu wxMenu;

    public String getName() {
        return menuName;
    }

    public void setName(String name) {
        this.menuName = name;
    }

    public String getType() {
        return menuType;
    }

    public void setType(String type) {
        this.menuType = type;
    }

    public String getKey() {
        return menuKey;
    }

    public void setKey(String key) {
        this.menuKey = key;
    }

    public String getUrl() {
        return menuUrl;
    }

    public void setUrl(String url) {
        this.menuUrl = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public WxMenu getWxMenu() {
        return wxMenu;
    }

    public void setWxMenu(WxMenu wxMenu) {
        this.wxMenu = wxMenu;
    }
}
