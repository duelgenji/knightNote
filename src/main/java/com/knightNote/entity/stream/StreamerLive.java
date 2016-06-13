package com.knightNote.entity.stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wonders.xlab.framework.entity.AbstractBaseEntity;

import javax.persistence.*;

/**
 * 主播 直播 信息 (实时性)
 * Created by knight on 16/6/12.
 */
@Entity
@Table
public class StreamerLive extends AbstractBaseEntity<Long> {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Streamer streamer;

    /**
     * 房间 名称
     */
    private String roomName;

    /**
     * 房间 封面
     */
    private String roomCover;

    /**
     * 是否 正在直播
     */
    private boolean isOnline;

    /**
     * 观众人数
     */
    private String onlineNumber;


    /**
     * 关注人数
     */
    private String followNumber;

    public Streamer getStreamer() {
        return streamer;
    }

    public void setStreamer(Streamer streamer) {
        this.streamer = streamer;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCover() {
        return roomCover;
    }

    public void setRoomCover(String roomCover) {
        this.roomCover = roomCover;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getOnlineNumber() {
        return onlineNumber;
    }

    public void setOnlineNumber(String onlineNumber) {
        this.onlineNumber = onlineNumber;
    }

    public String getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(String followNumber) {
        this.followNumber = followNumber;
    }
}
