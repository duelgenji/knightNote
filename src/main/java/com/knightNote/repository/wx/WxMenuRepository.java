package com.knightNote.repository.wx;

import com.knightNote.entity.wx.WxMenu;
import com.wonders.xlab.framework.repository.MyRepository;

import java.util.List;

/**
 * Created by duelgenji on 16/11/15.
 */
public interface WxMenuRepository extends MyRepository<WxMenu, Long> {

    List<WxMenu> findByWxMenuIsNull();

    List<WxMenu> findByWxMenu(WxMenu wxMenu);
}
