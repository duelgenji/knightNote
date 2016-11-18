package com.knightNote.controller.wx;

import com.knightNote.other.JsonResponseEntity;
import com.knightNote.service.wx.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信模板信息接口
 * Created by duelgenji on 16/11/18.
 */
@RestController
@RequestMapping("wx/template")
public class WxTemplateController {

    @Autowired
    private WxService wxService;

    @RequestMapping("send")
    public JsonResponseEntity<?> send(){
        return JsonResponseEntity.success(wxService.getMenu());
    }

}
