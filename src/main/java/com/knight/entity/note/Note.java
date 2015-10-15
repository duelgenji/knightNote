package com.knight.entity.note;

import com.knight.entity.user.User;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by knight on 15/10/9.
 */
@Entity
public class Note extends AbstractPersistable<Long> {

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private NoteTag noteTag;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public NoteTag getNoteTag() {
        return noteTag;
    }

    public void setNoteTag(NoteTag noteTag) {
        this.noteTag = noteTag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
