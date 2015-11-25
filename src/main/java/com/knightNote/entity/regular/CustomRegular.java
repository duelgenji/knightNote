package com.knightNote.entity.regular;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by Knight on 2015/11/24 22:38.
 */

//@Entity
//@Table
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class CustomRegular extends AbstractPersistable<Long> {

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

}
