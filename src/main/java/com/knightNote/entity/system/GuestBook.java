package com.knightNote.entity.system;

import com.wonders.xlab.framework.entity.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Knight on 2016/3/13 21:58.
 */
@Entity
@Table
public class GuestBook extends AbstractBaseEntity<Long> {

    private String name;

    private String email;

    @Column(columnDefinition = "TEXT")
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
