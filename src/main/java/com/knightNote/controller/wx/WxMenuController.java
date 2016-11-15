package com.knightNote.controller.wx;

import com.knightNote.service.wx.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by duelgenji on 16/11/15.
 */
@RestController
@RequestMapping("wx/menu")
public class WxMenuController {

    @Autowired
    private WxService wxService;

    @RequestMapping("fetch")
    public Map<String ,Object> fetch(){
        Map<String, Object> res = new HashMap<>();


        res.put("success", 1);
        res.put("token", wxService.getAccessToken());
        return res;
    }

}
