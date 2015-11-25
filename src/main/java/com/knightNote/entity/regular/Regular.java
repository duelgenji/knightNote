package com.knightNote.entity.regular;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 周 周期 定期 日常 习惯
 * Created by Knight on 2015/11/24 22:18.
 */
@Entity
@Table
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Regular  extends AbstractPersistable<Long> {

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
