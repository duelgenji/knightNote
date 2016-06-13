package com.knightNote.entity.stream;

import com.wonders.xlab.framework.entity.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 主播 信息
 * Created by knight on 16/6/12.
 */
@Entity
@Table
public class Streamer extends AbstractBaseEntity<Long> {

    /**
     * 主播 昵称
     */
    private String nickname;

    /**
     * 所在平台
     */
    private String platform;

    /**
     * 直播房间 链接
     */
    private String room;

    /**
     * 主播 头像
     */
    private String avatarUrl;

    /**
     * 主玩 游戏
     */
    private String majorGame;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "streamer")
    private StreamerLive streamerLive;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMajorGame() {
        return majorGame;
    }

    public void setMajorGame(String majorGame) {
        this.majorGame = majorGame;
    }

    public StreamerLive getStreamerLive() {
        return streamerLive;
    }

    public void setStreamerLive(StreamerLive streamerLive) {
        this.streamerLive = streamerLive;
    }
}
