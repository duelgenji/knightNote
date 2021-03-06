package com.knightNote.entity.note;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import java.util.Date;

/**
 * 标签
 * Created by knightNote on 15/10/15.
 */
@Entity
public class NoteTag extends AbstractPersistable<Long> {

    /**
     * 标签内容 如：书评、影评、日记、随笔、等 控制字符长度
     */
    private String content;

    private Date createDate = new Date();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
