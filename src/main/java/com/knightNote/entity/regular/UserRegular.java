package com.knightNote.entity.regular;

import com.knightNote.entity.user.User;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户参与的 regular
 * Created by Knight on 2015/11/24 23:02.
 */
@Entity
@Table
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class UserRegular extends AbstractPersistable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Regular regular;

    /**
     * 别名
     */
    private String alias;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Regular getRegular() {
        return regular;
    }

    public void setRegular(Regular regular) {
        this.regular = regular;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}