package com.knightNote.repository.system;

import com.knightNote.entity.system.WxAccessToken;
import com.wonders.xlab.framework.repository.MyRepository;

public interface WxAccessTokenRepository extends MyRepository<WxAccessToken, Long> {

    WxAccessToken findFirstByOrderByIdDesc();
}
