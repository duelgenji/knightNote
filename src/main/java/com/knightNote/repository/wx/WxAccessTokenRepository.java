package com.knightNote.repository.wx;

import com.knightNote.entity.wx.WxAccessToken;
import com.wonders.xlab.framework.repository.MyRepository;

public interface WxAccessTokenRepository extends MyRepository<WxAccessToken, Long> {

    WxAccessToken findFirstByOrderByIdDesc();
}
