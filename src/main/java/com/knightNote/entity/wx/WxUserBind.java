package com.knightNote.entity.wx;

import com.knightNote.entity.user.User;
import com.wonders.xlab.framework.entity.AbstractBaseEntity;

import javax.persistence.*;

/**
 * 微信用户绑定表
 * Created by duelgenji on 16/11/14.
 */
@Entity
@Table
public class WxUserBind extends AbstractBaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private WxUser wxUser;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WxUser getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }
}
