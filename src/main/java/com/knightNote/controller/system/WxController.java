package com.knightNote.controller.system;

import com.knightNote.entity.system.WxPostMessage;
import com.knightNote.repository.system.WxPostMessageRepository;
import com.knightNote.service.wx.WxService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by knight on 16/5/19.
 */
@RestController
@RequestMapping("wx")
public class WxController {

    @Autowired
    WxService wxService;

    @Autowired
    WxPostMessageRepository wxPostMessageRepository;

    @Transactional
    @RequestMapping("wx")
    public Map<String ,Object> wx(){
        Map<String, Object> res = new HashMap<>();

        res.put("success", 1);
        res.put("token", wxService.getAccessToken());
        return res;
    }

    @RequestMapping("message")
    public Map<String ,Object> message(
            @RequestBody String body
            ){
        Map<String, Object> res = new HashMap<>();

        try {
            Document document = DocumentHelper.parseText(body);
            Element nodeElement = document.getRootElement();
            List node = nodeElement.elements();
            for (Iterator it = node.iterator(); it.hasNext();) {
                Element elm = (Element) it.next();
                res.put(elm.getName(), elm.getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        res.put("success", 1);
        return res;
    }

    @RequestMapping("retrieveMessage")
    public void retrieveMessage(
            @RequestBody String body
    ){
        Map<String, String> res = new HashMap<>();

        try {

            WxPostMessage wxPostMessage = new WxPostMessage();

            Document document = DocumentHelper.parseText(body);
            Element nodeElement = document.getRootElement();
            List node = nodeElement.elements();
            for (Iterator it = node.iterator(); it.hasNext();) {
                Element elm = (Element) it.next();
                res.put(elm.getName(), elm.getText());

            }
            wxPostMessage.setToUserName(res.get("ToUserName"));
            wxPostMessage.setFromUserName(res.get("FromUserName"));
            wxPostMessage.setCreateTime(res.get("CreateTime"));
            wxPostMessage.setMsgType(res.get("MsgType"));
            wxPostMessage.setContent(res.get("Content"));
            wxPostMessage.setMsgId(res.get("MsgId"));
            wxPostMessageRepository.save(wxPostMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
