package com.knight.entity.note;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

/**
 * Created by knight on 15/10/15.
 */
@Entity
public class NoteTag extends AbstractPersistable<Long> {

    /**
     * 标签内容 如：书评、影评、日记、随笔、等 控制字符长度
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
