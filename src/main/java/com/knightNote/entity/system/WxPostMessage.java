package com.knightNote.entity.system;

import com.wonders.xlab.framework.entity.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 微信推送过来的消息
 * Created by knight on 16/5/20.
 */
@Entity
@Table
public class WxPostMessage extends AbstractBaseEntity<Long> {

    private String toUserName;

    private String fromUserName;

    private String createTime;

    private String msgType;

    private String content;

    private String msgId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
