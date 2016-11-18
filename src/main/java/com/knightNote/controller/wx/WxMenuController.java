package com.knightNote.controller.wx;

import com.knightNote.other.JsonResponseEntity;
import com.knightNote.service.wx.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信菜单接口
 * Created by duelgenji on 16/11/15.
 */
@RestController
@RequestMapping("wx/menu")
public class WxMenuController {

    @Autowired
    private WxService wxService;

    @RequestMapping("get")
    public JsonResponseEntity<?> get(){
        return JsonResponseEntity.success(wxService.getMenu());
    }

    @RequestMapping("fetch")
    public JsonResponseEntity<?> fetch(){
        return JsonResponseEntity.success(wxService.fetchMenu());
    }

    @RequestMapping("create")
    public JsonResponseEntity<?> create(){
        return JsonResponseEntity.success(wxService.createMenu());
    }

}
