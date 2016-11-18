package com.knightNote.dto.wx;

import com.knightNote.entity.wx.WxMenu;

import java.util.List;

/**
 * Created by duelgenji on 16/11/15.
 */
public class WxMenuDto {

    public List<Button> button;

    public static class Button{
        public Long id;
        public String name;
        public String type;
        public String key;
        public String url;
        public String media_id;
        public List<Button> sub_button;
    }

    public WxMenuDto entity2Dto(List<WxMenu> list){
        WxMenuDto dto = new WxMenuDto();

        return dto;
    }


}
