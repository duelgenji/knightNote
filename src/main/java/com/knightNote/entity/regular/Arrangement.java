package com.knightNote.entity.regular;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.knightNote.entity.user.User;
import com.wonders.xlab.framework.entity.AbstractBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 近期安排 计划
 * Created by knight on 16/3/16.
 */
@Entity
@Table
public class Arrangement extends AbstractBaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonIgnore
    private User user;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String remark;

    /**
     * 是否完成
     */
    private boolean complete;

    /**
     * 是否取消
     */
    private boolean cancel;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date completeDate;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}
