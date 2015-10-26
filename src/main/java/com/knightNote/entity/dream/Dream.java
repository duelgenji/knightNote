package com.knightNote.entity.dream;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * Created by knightNote on 15/10/9.
 */
//@Entity
public class Dream extends AbstractPersistable<Long> {

    @Column(columnDefinition = "TEXT")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
