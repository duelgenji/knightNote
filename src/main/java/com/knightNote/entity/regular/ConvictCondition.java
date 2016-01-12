package com.knightNote.entity.regular;

import com.knightNote.entity.user.User;
import com.wonders.xlab.framework.entity.AbstractBaseEntity;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

/**
 * 囚徒健身
 * Created by knight on 16/1/12.
 */
@Entity
@Table
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class ConvictCondition extends AbstractBaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    /**
     * 艺
     */
    private Progression progression;

    /**
     * 俯卧撑 深蹲 引体向上 举腿 桥 倒立撑
     */
    public enum Progression{
        PushUp,Pistol,PullUp,LegRaises,Bridges,HandStand
    }

    /**
     * 式  通常1-10  0是花式
     */
    private int step;

    /**
     * 花式名
     */
    private String stepName;

    /**
     * 级  通常1-3 0代表自定义级
     */
    private int level;

    /**
     * 计算类型  0次数 or 1时间(seconds)
     */
    private int countType;

    private int count;

    private String remark;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Progression getProgression() {
        return progression;
    }

    public void setProgression(Progression progression) {
        this.progression = progression;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCountType() {
        return countType;
    }

    public void setCountType(int countType) {
        this.countType = countType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
