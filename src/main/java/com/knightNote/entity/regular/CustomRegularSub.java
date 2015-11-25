package com.knightNote.entity.regular;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Knight on 2015/11/24 22:50.
 */
//@Entity
//@Table
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class CustomRegularSub extends AbstractPersistable<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private RegularSub regularSub;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

}
