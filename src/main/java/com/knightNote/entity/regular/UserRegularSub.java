package com.knightNote.entity.regular;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户参与的 regular sub
 * Created by Knight on 2015/11/24 23:02.
 */
@Entity
@Table
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class UserRegularSub extends AbstractPersistable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserRegular userRegular;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

    /**
     * 参与日期
     */
    @Temporal(TemporalType.DATE)
    private Date finishDate = new Date();

    /**
     * 数量  todo maybe 加上单位
     */
    private Integer count;

    /**
     * 备注
     */
    private String remark;

    public UserRegular getUserRegular() {
        return userRegular;
    }

    public void setUserRegular(UserRegular userRegular) {
        this.userRegular = userRegular;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}